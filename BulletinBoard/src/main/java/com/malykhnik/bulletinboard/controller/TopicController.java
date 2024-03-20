package com.malykhnik.bulletinboard.controller;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.dto.TopicDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.exception.MessageNotFound;
import com.malykhnik.bulletinboard.exception.NoUserPermissions;
import com.malykhnik.bulletinboard.exception.TopicNotFound;
import com.malykhnik.bulletinboard.exception.UserNotAuthenticated;
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
    public List<Message> getMessageInTopicById(@PathVariable int id) throws TopicNotFound {
        return topicService.getMessagesInTopicById(id);
    }

    @PostMapping("/createTopic")
    public void createTopic(@Valid @RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @PostMapping("/createMessageInTopic/{id}")
    public void createMessage(@Valid @RequestBody Message message,
                                      @PathVariable int id) throws TopicNotFound {
        topicService.addMessage(message, id);
    }

    @PutMapping("/updateMessage/{topicId}/{mesId}")
    public Message updateMessage(@Valid @RequestBody MessageDto messageDto,
                                @PathVariable int topicId,
                                 @PathVariable int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        return topicService.updateMessage(messageDto, topicId, mesId);
    }

    @PutMapping("/updateTopic/{topicId}")
    public Topic updateTopic(@RequestBody TopicDto topicDto,
                             @PathVariable int topicId) throws TopicNotFound {
        return topicService.updateTopic(topicDto, topicId);
    }

    @DeleteMapping("/deleteMessage/{topicId}/{mesId}")
    public Message deleteMessage(@PathVariable int topicId,
                              @PathVariable int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        return topicService.deleteMessage(topicId, mesId);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public Topic deleteTopic(@PathVariable int topicId) throws TopicNotFound {
        return topicService.deleteTopic(topicId);
    }
}
