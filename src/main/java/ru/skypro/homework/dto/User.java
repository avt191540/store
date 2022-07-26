package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
