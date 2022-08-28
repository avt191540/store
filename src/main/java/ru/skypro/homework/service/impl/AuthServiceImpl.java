package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Authorities;
import ru.skypro.homework.repo.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.AuthoritiesService;
import ru.skypro.homework.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserDetailsManager manager;

    private final UserRepository userRepository;

    private final AuthoritiesService authoritiesService;

    private final UserService userService;

    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager, UserRepository userRepository,
                           AuthoritiesService authoritiesService, UserService userService) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.authoritiesService = authoritiesService;
        this.userService = userService;
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * Authorization by username and password
     * @param username - get from client
     * @param password - get from client
     * @return type boolean result of login process
     */
    @Override
    public boolean login(String username, String password) {
        if (!userRepository.existsUserByUsername(username)) {
            return false;
        }
        String encryptedPassword = getEncryptedPassword(username);
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    /**
     * New user registration
     * @param registerReq get user information from client like RegisterReq DTO model
     * @param role user role
     * @return type boolean of registration
     */
    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReq.getPassword())
                        .username(registerReq.getUsername())
                        .roles(role.name())
                        .build()
        );
        registerNewUser(registerReq);
        return true;
    }

    @Override
    public void changePassword(String userName, String currentPassword, String newPassword) throws NotFoundException {
        String encryptedPassword = getEncryptedPassword(userName);
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        if (encoder.matches(currentPassword, encryptedPasswordWithoutEncryptionType)){
            userService.savePassword(userName, newPassword);
        }
        throw new AccessDeniedException("Your put down invalid password");
    }

    @Override
    public void registerNewUser(RegisterReq req){
        logger.info("Running method registerNewUser");

        ru.skypro.homework.model.User user = userRepository.getUserByUsername(req.getUsername()).get();
        user.setEmail(req.getUsername());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhone(req.getPhone());
        userService.updateUserRegistration(user);

        Authorities authority = authoritiesService.getAuthority(req.getUsername());
        authority.setUser(user);
        authoritiesService.saveAuthority(authority);
    }

    private String getEncryptedPassword(String userName) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return userDetails.getPassword();
    }
}
