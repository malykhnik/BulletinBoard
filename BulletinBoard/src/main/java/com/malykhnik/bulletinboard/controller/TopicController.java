package com.malykhnik.bulletinboard.controller;

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

    @GetMapping("/getMessageInTopic/{id}")
    public List<Message> getMessageInTopicById(@PathVariable int id) {
        return topicService.getMessageInTopicById(id);
    }

    @PostMapping("/createTopic")
    public void createTopic(@RequestBody @Valid Topic topic) {
        topicService.addTopic(topic);
    }

    @PostMapping("/createMessageInTopic/{id}")
    public void createMessage(@RequestBody @Valid Message message,
                                      @PathVariable int id) {
        topicService.addMessage(message, id);
    }

    @PutMapping("/updateMessage/{topicId}/{mesId}")
    public Message updateMessage(@RequestBody Message message,
                                @PathVariable int topicId,
                                 @PathVariable int mesId) {
        return topicService.updateMessage(message, topicId, mesId);
    }

    @DeleteMapping("/deleteMessage/{topicId}/{mesId}")
    public void deleteMessage(@PathVariable int topicId,
                              @PathVariable int mesId) {
        topicService.deleteMessage(topicId, mesId);
    }
}
