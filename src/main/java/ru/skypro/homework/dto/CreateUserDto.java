package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 5, message = "FirstName should be at least 5 chars")
    private String firstName;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 5, message = "FirstName should be at least 5 chars")
    private String lastName;

    @NotBlank
    private String password;

    @Size(min = 10, message = "Phone should be at least 10 chars")
    private String phone;



}
