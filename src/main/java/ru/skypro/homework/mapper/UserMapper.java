package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    User createUserDtoToUser(CreateUserDto createUser);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "id", target = "id")
    UserDto userToUserDto(User user);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "id", target = "id")
    User userDtoToUser(UserDto userDto);

    Collection<UserDto> entitiesToDto(Collection<User> user);
}
