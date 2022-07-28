package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdsCommentDto {
    private Long idAuthor;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;
}
