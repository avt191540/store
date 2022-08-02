package ru.skypro.homework.dto;

import lombok.Data;
@Data
public class CreateAdsDto {

    private String description;
    private String Image;
    private Integer pk;
    private Integer price;
    private String title;
}
