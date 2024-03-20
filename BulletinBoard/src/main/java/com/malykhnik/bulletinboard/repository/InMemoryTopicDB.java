package com.malykhnik.bulletinboard.repository;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
@AllArgsConstructor
@RequiredArgsConstructor
public class InMemoryTopicDB {
    @Setter
    private List<Topic> topics = new ArrayList<>();

    public List<Topic> getAllTopics() {
        return topics;
    }

    public List<Message> getMessagesInTopicById(int id) {
        return topics.get(this.findIdOfTopic(id)).getMessages();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addMessage(Message message, int id) {
        topics.get(this.findIdOfTopic(id)).getMessages().add(message);
    }

    public Message updateMessage(MessageDto messageDto, int topicId, int mesId) {
        Message message = topics.get(findIdOfTopic(topicId)).getMessages().get(findIdOfMessage(topicId, mesId));
        if (!message.getMessage().equals(messageDto.getMessage())) {
            message.setMessage(messageDto.getMessage());
        }
        return topics.get(findIdOfTopic(topicId)).getMessages().set(findIdOfMessage(topicId, mesId), message);
    }

    public Message deleteMessage(int topicId, int mesId) {
        return topics.get(this.findIdOfTopic(topicId)).getMessages().remove(this.findIdOfMessage(topicId, mesId));
    }

    public void deleteTopic(int topicId) {
        topics.remove(findIdOfTopic(topicId));
    }

    public int findIdOfTopic(int id) {
        return IntStream.range(0, topics.size())
                .filter(i -> topics.get(i).getId() == id)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("Топика с id = " + id + " не существует!"));
    }

    public int findIdOfMessage(int topicId, int mesId) {
        for (Topic topic : topics) {
            int index = IntStream.range(0, topic.getMessages().size())
                    .filter(j -> topic.getMessages().get(j).getId() == mesId && topic.getId() == topicId)
                    .findFirst()
                    .orElse(-1);
            if (index != -1) {
                return index;
            }
        }
        throw new IndexOutOfBoundsException("Сообщения с id = " + mesId + " в топике с id = " + topicId + " не существует!");
    }
}
