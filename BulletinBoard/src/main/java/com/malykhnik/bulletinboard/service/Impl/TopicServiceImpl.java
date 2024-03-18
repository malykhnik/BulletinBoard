package com.malykhnik.bulletinboard.service.Impl;

import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.repository.InMemoryTopicDB;
import com.malykhnik.bulletinboard.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .forEach(i -> topic.getMessages().get(i).setId(i+1));
        topicRepo.addTopic(topic);
    }

    @Override
    public void addMessage(Message message, int id) {
        topicRepo.addMessage(message, id);
    }

    @Override
    public Message updateMessage(Message message, int topicId, int mesId) {
        return topicRepo.updateMessage(message, topicId, mesId);
    }

    @Override
    public void deleteMessage(int topicId, int mesId) {
        topicRepo.deleteMessage(topicId, mesId);
    }
}
