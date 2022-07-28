package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.AdsComment;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/ads")
@Tag(name = "Контроллер Объявлений и Отзывов", description = "добавление, поиск, изменение и удаление Объявлений и Отзывов")
public class AdsController {

//    private final AdsService adsService;
//
//    public AdsController(AdsService adsService) {
//        this.adsService = adsService;
//    }

    /**
     * Получить все существующие объявления GET http://localhost:8080/ads
     **/
    @Operation(
            summary = "Получить все объявления",
            description = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявления получены"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Ads>> getAllAds() {
//        List<Ads> listOfAds = adsService.getAdsAll();
        List<Ads> listOfAds = null;
//        if (listOfAds.size() == 0) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(listOfAds);
    }

    /**
     * POST http://localhost:8080/ads
     * Создание объявления.
     * @param ads объявление
     * @return добавленное объявление в формате json
     */
    @Operation(
            summary = "Создать объявление",
            description = "Создание нового объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление создано"
                    )
            }
    )
    @PostMapping
    public Ads createAds(@RequestBody Ads ads) {
//        return adsService.addAds(ads);
        return ads;
    }

    /**
     * Удалить объявление по его идентификатору, то-есть по id. Должна одновременно
     * удаляться и соответствующая картинка (при ее наличии) в таблице картинок
     * DELETE http://localhost:8080/ads/{id}
     * @param id идентификатор
     **/
    @Operation(
            summary = "Удалить объявление",
            description = "Удаление объявления по его идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление удалено"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Ads> deleteAds(@PathVariable Integer id) {
//        adsService.deleteAds(id);
        return ok().build();
    }

    /**
     * Получить объявление по его идентификатору, то-есть по id
     * GET http://localhost:8080/ads/{id}
     * @param id идентификатор
     **/
    @Operation(
            summary = "Найти объявление",
            description = "Найти объявление по его идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление найдено"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ads> getAdsById(@PathVariable Integer id) {
//        Ads ads = adsService.getAdsById(id);
//        return ok(ads);
        return ok().build();
    }

    /** Редактировать объявление по его идентификатору,
     * PUT http://localhost:8080/ads/{id}
     * @param id идентификатор
     * @param ads
     **/
    @Operation(
            summary = "Изменить объявление",
            description = "Редактирование объявления с указанным идентификатором",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление отредактировано"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@RequestBody Ads ads, @PathVariable Integer id) {
//        Ads editAds = adsService.editAds(ads, id);
//        if (editAds == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ok(editAds);
        return ResponseEntity.ok(ads);
    }

    /**
     * POST http://localhost:8080/ads/{ad_pk}/comment
     * Создание отзыва(комментария) к объявлению. Объявление должно существовать.
     * Используется идентификатор объявления "ad_pk"
     * @param adsComment объявление
     * @param ad_pk идентификатор объявления
     * @return добавленное объявление в формате json
     */
    @Operation(
            summary = "Создать отзыв",
            description = "Создание нового отзыва к указанному объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отзыв создан"
                    )
            }
    )
    @PostMapping("/{ad_pk}/comment")
    public AdsComment createAdsComment(@RequestBody AdsComment adsComment, @PathVariable String ad_pk) {
        // проверить наличие такого объявления!
//        return adsCommentService.addAdsComment(adsComment);
        return adsComment;
    }

    /**
     * Получить все комментарии(отзывы) к объявлению GET http://localhost:8080/ads/{ad_pk}/comment
     * Объявление должно существовать. Используется идентификатор объявления "ad_pk"
     * @param ad_pk идентификатор объявления
     **/
    @Operation(
            summary = "Получить все отзывы к объявлению",
            description = "Нахождение всех отзывов к указанному объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отзывы получены"
                    )
            }
    )
    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<List<AdsComment>> getAdsComments(String ad_pk) {
//        List<AdsComment> listOfAdsComment = adsCommentService.getAdsComments(ad_pk);
        List<AdsComment> listOfAdsComment = null;
//        if (listOfAdsComment.size() == 0) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(listOfAdsComment);
    }

}
