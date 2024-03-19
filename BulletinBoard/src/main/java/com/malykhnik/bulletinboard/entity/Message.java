package com.malykhnik.bulletinboard.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class Message {

    private int id;
    private String author;
    @Size(min = 1)
    @NotNull(message = "Сообщение должно содержать информацию!")
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "У сообщения должна быть дата создания!")
    private final Date date;

    public Message(int id, String author, String message, Date date) {
        this.author = author;
        this.message = message;
        this.date = date;
    }

}
