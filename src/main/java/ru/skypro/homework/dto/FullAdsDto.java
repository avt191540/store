package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Picture;

import java.util.Collection;

@Data
public class FullAdsDto {
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private Collection<Picture> image;
    private String phone;
    private Long pk;
    private Integer price;
    private String title;

}