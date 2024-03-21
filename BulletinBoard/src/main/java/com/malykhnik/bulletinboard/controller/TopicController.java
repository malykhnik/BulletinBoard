package com.malykhnik.bulletinboard.controller;

import com.malykhnik.bulletinboard.dto.MessageDto;
import com.malykhnik.bulletinboard.dto.TopicDto;
import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.exception.*;
import com.malykhnik.bulletinboard.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bulletinBoard")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/getAllTopics")
    public ResponseEntity<List<Topic>> getAllTopics(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "3") int pageSize) throws IllegalPageSizeException {
        return ResponseEntity.ok(topicService.getAllTopics(page, pageSize));
    }

    @GetMapping("/getMessagesInTopic/{id}")
    public ResponseEntity<List<Message>> getMessageInTopicById(@PathVariable int id,
                                                               @RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "3") int pageSize) throws TopicNotFound, IllegalPageSizeException {
        return ResponseEntity.ok(topicService.getMessagesInTopicById(id, page, pageSize));
    }

    @PostMapping("/createTopic")
    public ResponseEntity<Void> createTopic(@Valid @RequestBody Topic topic) {
        topicService.addTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/createMessageInTopic/{id}")
    public ResponseEntity<Void> createMessage(@Valid @RequestBody Message message,
                                              @PathVariable int id) throws TopicNotFound {
        topicService.addMessage(message, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateMessage/{topicId}/{mesId}")
    public ResponseEntity<Message> updateMessage(@Valid @RequestBody MessageDto messageDto,
                                                 @PathVariable int topicId,
                                                 @PathVariable int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        return ResponseEntity.ok(topicService.updateMessage(messageDto, topicId, mesId));
    }

    @PutMapping("/updateTopic/{topicId}")
    public ResponseEntity<Topic> updateTopic(@RequestBody TopicDto topicDto,
                                             @PathVariable int topicId) throws TopicNotFound {
        return ResponseEntity.ok(topicService.updateTopic(topicDto, topicId));
    }

    @DeleteMapping("/deleteMessage/{topicId}/{mesId}")
    public ResponseEntity<Message> deleteMessage(@PathVariable int topicId,
                                                 @PathVariable int mesId) throws UserNotAuthenticated, NoUserPermissions, TopicNotFound, MessageNotFound {
        return ResponseEntity.ok(topicService.deleteMessage(topicId, mesId));
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable int topicId) throws TopicNotFound {
        return ResponseEntity.ok(topicService.deleteTopic(topicId));
    }
}

