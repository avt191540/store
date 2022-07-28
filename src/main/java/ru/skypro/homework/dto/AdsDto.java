package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDto {
    private Long idAuthor;
    private String title;
    private String image;
    private Long pk;
    private Integer price;
}
