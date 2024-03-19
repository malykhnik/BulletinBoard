package com.malykhnik.bulletinboard.service.Impl;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.repository.InMemoryTopicDB;
import com.malykhnik.bulletinboard.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final InMemoryTopicDB topicRepo;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepo.getAllTopics();
    }

    @Override
    public List<Message> getMessagesInTopicById(int id) {
        return topicRepo.getMessagesInTopicById(id);
    }

    @Override
    public void addTopic(Topic topic) {
        topic.setId(topicRepo.getAllTopics().size() + 1);
        IntStream.range(0, topic.getMessages().size())
                .forEach(i -> topic.getMessages().get(i).setId(i + 1));
        topic.getMessages()
                .forEach(message -> message
                        .setAuthor(SecurityContextHolder.getContext().getAuthentication().getName()));
        topicRepo.addTopic(topic);
    }

    @Override
    public void addMessage(Message message, int id) {
        message.setId(topicRepo.getMessagesInTopicById(id).size() + 1);
        message.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        topicRepo.addMessage(message, id);
    }

    @Override
    public Message updateMessage(MessageDto messageDto, int topicId, int mesId) {
        if (hasAdminRole()) {
            return topicRepo.updateMessage(messageDto, topicId, mesId);
        }
        String messageAuthor = topicRepo.
                getMessagesInTopicById(topicId).
                get(topicRepo.findIdOfMessage(topicId,mesId))
                .getAuthor();
        if (messageAuthor.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return topicRepo.updateMessage(messageDto, topicId, mesId);
        }
        throw new UsernameNotFoundException("Пользователь с username = " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                " не имеет доступа к редактированию данного message!");
    }

    @Override
    public void deleteMessage(int topicId, int mesId) {
        if (hasAdminRole()) {
            topicRepo.deleteMessage(topicId, mesId);
        }
        String messageAuthor = topicRepo.
                getMessagesInTopicById(topicId).
                get(topicRepo.findIdOfMessage(topicId,mesId))
                .getAuthor();
        if (messageAuthor.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            topicRepo.deleteMessage(topicId, mesId);
        }
        throw new UsernameNotFoundException("Пользователь с username = " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                " не имеет доступа к редактированию данного message!");
    }

    public void deleteTopic(int topicId) {
        topicRepo.deleteTopic(topicId);
    }

    private boolean hasAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> userRoles = new HashSet<>();

        if (authentication != null) {
            authentication.getAuthorities().forEach(authority -> {
                userRoles.add(authority.getAuthority());
            });
        }

        return userRoles.contains("ROLE_admin");
    }
}
