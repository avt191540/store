package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AdsCommentDto {
    @Min((1))
    private Long idAuthor;

    private LocalDateTime createdAt;

    @Min((1))
    private Long pk;

    @Size(max = 1_000, message = "Description should be at least 1_000 chars")
    private String text;
}
