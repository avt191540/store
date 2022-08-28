package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CreateUserDto;
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

    public UserDto updateUser(String userName,UserDto userDto) throws NotFoundException {
        logger.info("Method updateUser was started");
        User user = userRepository.getUserByUsername(userName).orElseThrow(NotFoundException::new);
        if (userDto.getFirstName() != null || !userDto.getFirstName().isEmpty()) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null || !userDto.getLastName().isEmpty()) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null || !userDto.getPhone().isEmpty()) {
            user.setPhone(userDto.getPhone());
        }
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
    public void savePassword(String userName, String newPassword) throws NotFoundException {
        if (userRepository.existsUserByEmail(userName)) {
            userRepository.updatePassword(userName, newPassword);
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
    public Collection<UserDto> getAllUsers() {
        return userMapper.entitiesToDto(userRepository.getAll().get());
    }
}
