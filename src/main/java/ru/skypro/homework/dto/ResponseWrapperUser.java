package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperUser {
    private Integer count;
    private Collection<UserDto> results;
}
