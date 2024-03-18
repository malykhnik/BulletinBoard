package com.malykhnik.bulletinboard.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Topic {

    private int id;
    @Size(min = 1)
    @NotNull(message = "Топик должен иметь заголовок")
    private String title;
    @Size(min = 1)
    @NotNull(message = "Топик должен содержать как минимум одно сообщение!")
    private List<@Valid Message> messages;

    public Topic(int id, String title, List<Message> messages) {
        this.title = title;
        this.messages = messages;
    }
}
