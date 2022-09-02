package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.service.PictureService;
import ru.skypro.homework.service.impl.PictureServiceImpl;

import javax.validation.constraints.Min;
import java.io.IOException;

/** Эндпоинты для работы с таблицей картинок для объявлений (picture), в которой
 * находятся картинки для всех объявлений
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@Tag(name = "Контроллер Картинок", description = "добавление, поиск, и удаление Картинок для объявлений")
public class PictureController {

    private final Logger logger = LoggerFactory.getLogger(PictureController.class);
    private final PictureService pictureService;

    public PictureController(PictureServiceImpl pictureService) {
        this.pictureService = pictureService;
    }

    /**
     * Загрузка картинки для объявления с указанием идентификатора объявления
     * @param idAds идентификатор объявления
     * @param file картинка
     */
    @Operation(
            summary = "Загрузка картинки",
            description = "Позволяет загрузить в БД картинку для указанного объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Картинка загружена"
                    )
            }
    )
    @PostMapping(value = "/upl/{idAds}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Picture> uploadAdsPicture(@PathVariable @Min(1) Long idAds,
                                                    @RequestParam MultipartFile file) {
        logger.info("Method uploadAdsPicture is running: {}", idAds);
        Picture picture;
        try{
            picture = pictureService.uploadAdsPicture(idAds, file);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IOException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(picture);
    }

    @PostMapping(value = "/api/picture/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Picture> updateAdsPicture(@PathVariable @Min(1) Long id,
                                                    @RequestParam MultipartFile file){
        Picture picture;
        try{
            picture = pictureService.updateAdsPicture(id, file);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IOException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(picture);
    }

    /**
     * Получить картинку по ее id (id)
     * @param id идентификатор картинки
     */
    @Operation(
            summary = "Поиск картинки",
            description = "Позволяет найти картинку по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденная картинка"
                    )
            }
    )
    @GetMapping(value = "/api/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getPicture(@PathVariable("id") @Min(1) Long id) {
        logger.info("Method downloadPicture is running: {}", id);
        byte[] picture;
        try{
            picture = pictureService.getPicture(id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(picture);
    }
}
