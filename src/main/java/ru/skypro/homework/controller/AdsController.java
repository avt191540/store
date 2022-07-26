package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/ads")
public class AdsController {

//    private final AdsService adsService;
//
//    public AdsController(AdsService adsService) {
//        this.adsService = adsService;
//    }

    /**
     * Получить все существующие объявления GET http://localhost:8080/ads
     **/
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
    @PostMapping
    public Ads createAds(@RequestBody Ads ads) {
//        return adsService.addReport(ads);
        return ads;
    }

    /**
     * Удалить объявление по его идентификатору, то-есть по id. Должна одновременно
     * удаляться и соответствующая картинка (при ее наличии) в таблице картинок
     * DELETE http://localhost:8080/ads/{id}
     * @param id идентификатор
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Ads> deleteAds(@PathVariable Long id) {
//        adsService.deleteAds(id);
        return ok().build();
    }

    /**
     * Получить объявление по его идентификатору, то-есть по id
     * GET http://localhost:8080/ads/{id}
     * @param id идентификатор
     **/
    @GetMapping("/{id}")
    public ResponseEntity<Ads> getAdsById(@PathVariable long id) {
//        Ads ads = adsService.getAdsById(id);
//        return ok(ads);
        return ok().build();
    }

    /** Редактировать объявление по его идентификатору,
     * PUT http://localhost:8080/ads/{id}
     * @param id идентификатор
     * @param ads
     **/
    @PutMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@RequestBody Ads ads, @PathVariable long id) {
//        Ads editAds = adsService.editAds(ads);
//        if (editAds == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ok(editAds);
        return ResponseEntity.ok(ads);
    }
}
