package com.malykhnik.bulletinboard.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Topic {
    @NotNull
    @Min(0)
    private int id;
    @NotNull(message = "Топик должен иметь заголовок")
    private String title;
    @NotEmpty(message = "Топик должен содержать как минимум одно сообщение!")
    private List<Message> messages;
}
