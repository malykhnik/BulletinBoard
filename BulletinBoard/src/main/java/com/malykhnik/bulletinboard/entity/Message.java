package com.malykhnik.bulletinboard.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Message {
    @NotNull
    @Min(0)
    private int id;
    @NotNull(message = "Сообщение должно содержать имя автора!")
    private String author;
    @NotNull(message = "Сообщение должно содержать информацию!")
    private String message;
    @NotNull(message = "У сообщения должна быть дата создания!")
    private Date date;
}
