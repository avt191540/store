package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.IdNotFoundException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.PictureRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class PictureService {

    private final Logger logger = LoggerFactory.getLogger(PictureService.class);
    private PictureRepository pictureRepository;
    private AdsRepository adsRepository;

    public PictureService(PictureRepository pictureRepository, AdsRepository adsRepository) {
        this.pictureRepository = pictureRepository;
        this.adsRepository = adsRepository;
    }

    public void uploadAdsPicture(Long id_ads, MultipartFile pictureFile) throws IOException {

        logger.info("Method was called - uploadPetPhoto");
        Ads ads = adsRepository.findById(id_ads).
                orElseThrow(() -> new IdNotFoundException
                        ("Такого идентификатора в таблице shelter_pets не существует" + id_ads));
        Picture picture = new Picture();
        picture.setAds(ads);
        picture.setFileSize(pictureFile.getSize());
        picture.setMediaType(pictureFile.getContentType());
        picture.setData(pictureFile.getBytes());
        pictureRepository.save(picture);
    }

    public Picture findPictureById(Long id_picture) {
        Picture picture = pictureRepository.findById(id_picture).orElse(new Picture());
        return picture;
    }

    public void deletePicture(Long id_picture) {
        Picture picture = pictureRepository.findById(id_picture)
                .orElseThrow(()-> new IdNotFoundException("Такого идентификатора в таблице pictures не существует" + id_picture));
        pictureRepository.deleteById(id_picture);
    }

    public void deleteAll() {
        pictureRepository.deleteAll();
    }
}
