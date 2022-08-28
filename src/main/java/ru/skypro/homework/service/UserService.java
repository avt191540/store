package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.User;

import java.util.Collection;

public interface UserService {
    UserDto addUser(CreateUserDto createUser);

    UserDto updateUser(String userName, UserDto userDto)throws NotFoundException;

    UserDto getUserById(Long id);

    void savePassword(String userName, String password);

    void updateUserRegistration(User user);

    Collection<UserDto> getAllUsers();
}
