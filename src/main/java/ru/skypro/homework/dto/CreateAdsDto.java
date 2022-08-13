package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateAdsDto {

    @NotBlank(message = "Description shouldn`t be blank")
    @Size(max = 700, message = "FirstName should be at least 500 chars")
    private String description;

    @Min((1))
    private Long idAuthor;

    @Min(1)
    private Integer price;

    @NotBlank
    private String title;
}
