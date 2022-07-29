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
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Контроллер пользователей", description = "добавление, обновление, удаление и другие операции с пользователями")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * POST <a href="http://localhost:3000/users">...</a>
     * Добавление пользователя.
     * @param user пользователь
     * @return добавленный пользователь в формате json
     */
    @Operation(
            summary = "Добавление нового пользователя",
            description = "Позволяет добавить пользователя в базу данных"
    )
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        logger.info("Method addUser is running: {}", user);
        return ResponseEntity.ok(user);
    }

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
    @GetMapping("{/id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        logger.info("Method getUser is running: {}", id);
        UserDto user = new UserDto();
        return ResponseEntity.ok(user);
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
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user){
        logger.info("Method updateUser is running: {}", user);
        UserDto newUser = new UserDto();
        return ResponseEntity.ok(newUser);
    }

    /**
     * Установить новый пароль GET <a href="http://localhost:3000/users">...</a>
     * @param currentPassword текущий пароль
     * @param newPassword новый пароль
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
    @PostMapping(params = "newPassword")
    public ResponseEntity<?> setPassword(@RequestParam(value = "currentPassword") String currentPassword,
                                         @RequestParam(value = "newPassword") String newPassword){
        logger.info("Method setPassword is running: {} {}", currentPassword, newPassword);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Получить всех пользователей GET <a href="http://localhost:3000/users">...</a>
     **/
    @Operation(
            summary = "Получить всех пользователей",
            description = "Позволяет получить всех пользователей из базы данных"
    )
    @GetMapping("/me")
    public ResponseEntity<Collection<UserDto>> getUsers(){
        logger.info("Method getUsers is running");
        Collection<UserDto> users = new ArrayList<>();
        if (!users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
}
