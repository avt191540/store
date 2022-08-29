package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginReq {
    @Size(min = 6)
    private String password;
    @Email
    @NotBlank
    private String username;

}
