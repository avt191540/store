package ru.skypro.homework.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PictureDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.service.impl.PictureService;

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
    @PostMapping(value = "/photo/{idAds}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PictureDto> uploadAdsPicture(@PathVariable Long idAds,
                                                       @RequestParam MultipartFile adsPicture) {
        PictureDto pictureDto;
        try{
            pictureDto = pictureService.uploadAdsPicture(idAds, adsPicture);
        } catch (IOException | NotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pictureDto);
    }

    /**
     * Получить картинку по ее id (id)
     */
    @GetMapping(value = "/photo/{id}")
    public ResponseEntity<byte[]> downloadPicture(@PathVariable Long id) {
        Picture picture = pictureService.findPictureById(id);
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
    public ResponseEntity<Picture> delete(@RequestParam(required = false) Long idPicture,
                                          @RequestParam(required = false) boolean all) {
        if (all) {
            pictureService.deleteAll();
            return ok().build();
        }
        if (idPicture != null) {
            pictureService.deletePicture(idPicture);
            return ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
