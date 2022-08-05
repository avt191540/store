package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.service.PictureService;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

/** Эндпоинты для работы с таблицей картинок для объявлений (picture), в которой
 * находятся картинки для всех объявлений
 */
@RestController
public class PictureController {


    private final PictureService pictureService;


    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    /**
     * Загрузка картинки для объявления с указанием идентификационного номера объявления
     */
    @PostMapping(value = "/photo/{id_ads}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAdsPicture(@PathVariable Long id_ads,
                                                 @RequestParam MultipartFile adsPicture) throws IOException {
        pictureService.uploadAdsPicture(id_ads, adsPicture);
        return ResponseEntity.ok().build();
    }

    /**
     * Получить картинку по ее id (id)
     */
    @GetMapping(value = "/photo/{id_picture}")
    public ResponseEntity<byte[]> downloadPicture(@PathVariable Long id_picture) {
        Picture picture = pictureService.findPictureById(id_picture);
        if (picture.getMediaType() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(picture.getMediaType()));
        headers.setContentLength(picture.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture.getData());
    }

    /**
     * Запрос на удаление картинок. Если задан критерий на удаление всех картинок (all = true),
     * тогда удаляются все фото, если (all = false) (установлено по умолчанию) и указан
     * идентификатор картинки (id_зшсегку), тогда удаляется одна картинка с указанным id
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Picture> delete(@RequestParam(required = false) Long id_picture,
                                                    @RequestParam(required = false) boolean all) {
        if (all) {
            pictureService.deleteAll();
            return ok().build();
        }
        if (id_picture != null) {
            pictureService.deletePicture(id_picture);
            return ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
