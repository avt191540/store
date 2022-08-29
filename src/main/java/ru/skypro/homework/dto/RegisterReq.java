package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterReq {
    @Email
    private String username;
    @Size(min = 6)
    private String password;
    @NotBlank
    @Size(min = 3)
    private String firstName;
    @NotBlank
    @Size(min = 3)
    private String lastName;
    @Pattern(regexp = "((\\d{1,3}?|\\+\\d\\s?|\\+?|\\+\\d{3}\\s?|\\(?|-?)?\\d{2}(\\(?\\d{2}\\)?[\\- ]?)?[\\d\\- ]{7,10})")
    private String phone;
    private Role role;
}
