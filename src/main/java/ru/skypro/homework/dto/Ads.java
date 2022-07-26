package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private Long idAuthor;
    private String title;
    private String image;
    private Integer pk;
    private Integer price;
}
