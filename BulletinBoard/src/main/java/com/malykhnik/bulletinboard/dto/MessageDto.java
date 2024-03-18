package com.malykhnik.bulletinboard.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageDto {

    @Size(min = 1)
    @NotNull(message = "Сообщение должно содержать имя автора!")
    private String author;
    @Size(min = 1)
    @NotNull(message = "Сообщение должно содержать информацию!")
    private String message;

}