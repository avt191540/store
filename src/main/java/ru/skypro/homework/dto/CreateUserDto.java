package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    private String firstName;

    @NotBlank(message = "FirstName shouldn`t be blank")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    private String lastName;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Pattern(regexp = "((\\d{1,3}?|\\+\\d\\s?|\\+?|\\+\\d{3}\\s?|\\(?|-?)?\\d{2}(\\(?\\d{2}\\)?[\\- ]?)?[\\d\\- ]{7,10})")
    private String phone;



}
