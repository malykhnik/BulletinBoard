package com.malykhnik.bulletinboard.service.Impl;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.dto.TopicDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.exception.*;
import com.malykhnik.bulletinboard.repository.InMemoryTopicDB;
import com.malykhnik.bulletinboard.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final InMemoryTopicDB topicRepo;

    @Override
    public List<Topic> getAllTopics(int page, int pageSize) throws IllegalPageSizeException {
        List<Topic> allTopics = topicRepo.getAllTopics();

        if (page < 1 || pageSize < 1) {
            throw new IllegalPageSizeException("Номер страницы и количество записей в странице не может быть меньше 1!");
        }

        if (pageSize > 10) {
            pageSize = 10;
        }
        page -= 1;

        int beginIdOfPage = page * pageSize;
        int endIdOfPage = Math.min(beginIdOfPage + pageSize, allTopics.size());

        return allTopics.subList(beginIdOfPage, endIdOfPage);
    }

    @Override
    public List<Message> getMessagesInTopicById(int id, int page, int pageSize) throws TopicNotFound, IllegalPageSizeException {
        List<Message> allMessages = topicRepo.getMessagesInTopicById(id);

        if (page < 1 || pageSize < 1) {
            throw new IllegalPageSizeException("Номер страницы и количество записей в странице не может быть меньше 1!");
        }

        if (pageSize > 10) {
            pageSize = 10;
        }
        page -= 1;

        int beginIdOfPage = page * pageSize;
        int endIdOfPage = Math.min(beginIdOfPage + pageSize, allMessages.size());

        return allMessages.subList(beginIdOfPage, endIdOfPage);
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
    public void addMessage(Message message, int id) throws TopicNotFound {
        message.setId(topicRepo.getMessagesInTopicById(id).size() + 1);
        message.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        topicRepo.addMessage(message, id);
    }

    @Override
    public Message updateMessage(MessageDto messageDto, int topicId, int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        if (hasAdminRole()) {
            return topicRepo.updateMessage(messageDto, topicId, mesId);
        }
        String messageAuthor = topicRepo.
                getMessagesInTopicById(topicId).
                get(topicRepo.findIdOfMessage(topicId, mesId))
                .getAuthor();
        if (messageAuthor.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return topicRepo.updateMessage(messageDto, topicId, mesId);
        }
        throw new NoUserPermissions("Пользователь с username = " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                " не имеет доступа к редактированию данного message!");
    }

    @Override
    public Message deleteMessage(int topicId, int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        if (hasAdminRole()) {
            topicRepo.deleteMessage(topicId, mesId);
        }
        String messageAuthor = topicRepo.
                getMessagesInTopicById(topicId).
                get(topicRepo.findIdOfMessage(topicId, mesId))
                .getAuthor();
        if (messageAuthor.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return topicRepo.deleteMessage(topicId, mesId);
        }
        throw new NoUserPermissions("Пользователь с username = " +
                SecurityContextHolder.getContext().getAuthentication().getName() +
                " не имеет доступа к редактированию данного message!");
    }

    @Override
    public Topic updateTopic(TopicDto topicDto, int topicId) throws TopicNotFound {
        return topicRepo.updateTopic(topicDto, topicId);
    }

    public Topic deleteTopic(int topicId) throws TopicNotFound {
        return topicRepo.deleteTopic(topicId);
    }

    private boolean hasAdminRole() throws UserNotAuthenticated {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> userRoles = new HashSet<>();

        if (authentication != null) {
            authentication.getAuthorities().forEach(authority -> {
                userRoles.add(authority.getAuthority());
            });
        } else {
            throw new UserNotAuthenticated("Пользователь не аутентифицирован!");
        }

        return userRoles.contains("ROLE_admin");
    }

}
