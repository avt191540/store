package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NewPasswordDto {
    private String currentPassword;
    @Size(min = 6)
    private String newPassword;
}
