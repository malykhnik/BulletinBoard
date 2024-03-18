package com.malykhnik.bulletinboard.repository;

import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Repository
@AllArgsConstructor
@RequiredArgsConstructor
public class InMemoryTopicDB {
    private List<Topic> topics = new ArrayList<>();

    public List<Topic> getAllTopics() {
        return topics;
    }

    public List<Message> getMessageInTopicById(int id) {
        return topics.get(this.findIdOfTopic(id)).getMessages();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addMessage(Message message, int id) {
        topics.get(this.findIdOfTopic(id)).getMessages().add(message);
    }

    public Message updateMessage(Message message, int topicId, int mesId) {
        return topics.get( this.findIdOfTopic(topicId)).getMessages().set(this.findIdOfMessage(mesId), message);
    }

    public void deleteMessage(int topicId, int mesId) {
        topics.get(this.findIdOfTopic(topicId)).getMessages().remove(this.findIdOfMessage(mesId));
    }

    private int findIdOfTopic(int id) {
        return IntStream.range(0, topics.size())
                .filter(i -> topics.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    private int findIdOfMessage(int id) {
        for (Topic topic : topics) {
            int index = IntStream.range(0, topic.getMessages().size())
                    .filter(j -> topic.getMessages().get(j).getId() == id)
                    .findFirst()
                    .orElse(-1);
            if (index != -1) {
                return index;
            }
        }
        return -1;
    }
}
