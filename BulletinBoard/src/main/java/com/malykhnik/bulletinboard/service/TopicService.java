package com.malykhnik.bulletinboard.service;

import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics();

    List<Message> getMessagesInTopicById(int id);

    void addTopic(Topic topic);

    void addMessage(Message message, int id);

    Message updateMessage(Message message, int topicId, int mesId);

    void deleteMessage(int topicId, int mesId);
}
