package com.malykhnik.bulletinboard.service;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics();

    List<Message> getMessagesInTopicById(int id);

    void addTopic(Topic topic);

    void addMessage(Message message, int id);

    Message updateMessage(MessageDto messageDto, int topicId, int mesId);

    Message deleteMessage(int topicId, int mesId);
    void deleteTopic(int topicId);
}
