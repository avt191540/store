package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @Min((1))
    private Long id;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    private String firstName;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    private String lastName;

    @Pattern(regexp = "((\\d{1,3}?|\\+\\d\\s?|\\+?|\\+\\d{3}\\s?|\\(?|-?)?\\d{2}(\\(?\\d{2}\\)?[\\- ]?)?[\\d\\- ]{7,10})")
    private String phone;

    @Email
    private String email;
}
