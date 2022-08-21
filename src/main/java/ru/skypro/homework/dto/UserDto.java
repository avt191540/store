package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @Min((1))
    private Long id;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 5, message = "FirstName should be at least 5 chars")
    private String firstName;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 5, message = "FirstName should be at least 5 chars")
    private String lastName;

    @Size(min = 10, message = "Phone should be at least 10 chars")
    private String phone;

    @Email
    private String email;
}
