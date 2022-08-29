package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.User;

public interface UserService {
    UserDto addUser(CreateUserDto createUser);

    UserDto updateUser(String userName, UserDto userDto)throws NotFoundException;

    UserDto getUserById(Long id);

    void savePassword(String userName, String password);

    void updateUserRegistration(User user);

    ResponseWrapperUser getAllUsers();
}
