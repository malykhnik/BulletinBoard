package com.malykhnik.bulletinboard.controller;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bulletinBoard")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/getAllTopics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/getMessagesInTopic/{id}")
    public List<Message> getMessageInTopicById(@PathVariable int id) {
        return topicService.getMessagesInTopicById(id);
    }

    @PostMapping("/createTopic")
    public void createTopic(@Valid @RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @PostMapping("/createMessageInTopic/{id}")
    public void createMessage(@Valid @RequestBody Message message,
                                      @PathVariable int id) {
        topicService.addMessage(message, id);
    }

    @PutMapping("/updateMessage/{topicId}/{mesId}")
    public Message updateMessage(@Valid @RequestBody MessageDto messageDto,
                                @PathVariable int topicId,
                                 @PathVariable int mesId) {
        return topicService.updateMessage(messageDto, topicId, mesId);
    }

    @DeleteMapping("/deleteMessage/{topicId}/{mesId}")
    public void deleteMessage(@PathVariable int topicId,
                              @PathVariable int mesId) {
        topicService.deleteMessage(topicId, mesId);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public void deleteTopic(@PathVariable int topicId) {
        topicService.deleteTopic(topicId);
    }
}
