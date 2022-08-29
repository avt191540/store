package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repo.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto addUser(CreateUserDto createUserDto){
        User user = userMapper.createUserDtoToUser(createUserDto);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public UserDto updateUser(String username,UserDto userDto) throws NotFoundException {
        logger.info("Method updateUser was started");
        User user = userRepository.getUserByUsername(username).orElseThrow(NotFoundException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) throws NotFoundException {
        User foundUser = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return userMapper.userToUserDto(foundUser);
    }

    @Transactional
    @Override
    public void savePassword(String username, String newPassword) throws NotFoundException {
        if (userRepository.existsUserByUsername(username)) {
            userRepository.updatePassword(username, newPassword);
        }
        throw new NotFoundException();
    }

    @Transactional
    @Override
    public void updateUserRegistration(User user) {
        userRepository.updateUserRegistration(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getPhone(), user.getId());
    }

    @Override
    public ResponseWrapperUser getAllUsers() {
        Collection<UserDto> usersDto = userMapper.entitiesToDto(userRepository.getAll());
        ResponseWrapperUser wrapperUsers = new ResponseWrapperUser();
        wrapperUsers.setCount(usersDto.size());
        wrapperUsers.setResults(usersDto);
        return wrapperUsers;
    }
}
