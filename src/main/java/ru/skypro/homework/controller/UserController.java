package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@Tag(name = "Контроллер пользователей", description = "добавление, обновление, удаление и другие операции с пользователями")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final AuthService authService;

    /**
     * Получить пользователя по его идентификатору, то-есть по id
     * GET <a href="http://localhost:3000/users/">...</a>{id}
     * @param id идентификатор
     **/
    @Operation(
            summary = "Поиск пользователя",
            description = "Позволяет найти пользователя по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный пользователь"
                    )
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUser(@PathVariable @Min(1) Long id){
        logger.info("Method getUser is running: {}", id);
        UserDto foundUserDto;
        try {
            foundUserDto = userService.getUserById(id);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUserDto);
    }

    /** Редактировать пользователя,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     * @param user пользователь, которого надо обновить
     **/
    @Operation(
            summary = "Обновить пользователя",
            description = "Позволяет обновить данные пользователя находящегося в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь обновлен"
                    )
            }
    )
    @PatchMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto user){
        logger.info("Method updateUser is running: {}", user);
        UserDto updateUser;
        try {
            updateUser = userService.updateUser(user);
        }
        catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (AccessDeniedException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(updateUser);
    }

    /**
     * Установить новый пароль GET <a href="http://localhost:3000/users">...</a>
     * @param password новый пароль
     **/
    @Operation(
            summary = "Установить новый пароль",
            description = "Позволяет установить новый пароль пользователю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый пароль установлен"
                    )
            }
    )
    @PostMapping("/set_password")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto password){
        logger.info("Method setPassword is running: {}", password);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            authService.changePassword(auth.getName(), password.getCurrentPassword(), password.getNewPassword());
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(password);
    }

    /**
     * Получить всех пользователей GET <a href="http://localhost:3000/users">...</a>
     **/
    @Operation(
            summary = "Получить всех пользователей",
            description = "Позволяет получить всех пользователей из базы данных"
    )
    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapperUser> getUsers(){
        logger.info("Method getUsers is running");
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
