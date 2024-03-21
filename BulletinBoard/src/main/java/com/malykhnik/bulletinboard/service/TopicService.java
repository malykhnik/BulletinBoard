package com.malykhnik.bulletinboard.service;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.dto.TopicDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.exception.*;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics(int page, int pageSize) throws IllegalPageSizeException;

    List<Message> getMessagesInTopicById(int id, int page, int pageSize) throws TopicNotFound, IllegalPageSizeException;

    void addTopic(Topic topic);

    void addMessage(Message message, int id) throws TopicNotFound;

    Message updateMessage(MessageDto messageDto, int topicId, int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound;

    Message deleteMessage(int topicId, int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound;
    Topic deleteTopic(int topicId) throws TopicNotFound;
    Topic updateTopic(TopicDto topicDto, int topicId) throws TopicNotFound;
}
