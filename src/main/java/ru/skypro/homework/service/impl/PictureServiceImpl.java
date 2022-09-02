package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.repo.AdsRepository;
import ru.skypro.homework.repo.PictureRepository;
import ru.skypro.homework.service.PictureService;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);
    private final PictureRepository pictureRepository;
    private final AdsRepository adsRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, AdsRepository adsRepository) {
        this.pictureRepository = pictureRepository;
        this.adsRepository = adsRepository;
    }


    @Override
    public Picture uploadAdsPicture(Long idAds, MultipartFile file) throws NotFoundException, IOException {
        logger.info("Method uploadAdsPicture was running: {}", idAds);
        Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
        return savePicture(ads, file);
    }

    @Override
    public Picture updateAdsPicture(Long idAds, MultipartFile file) throws NotFoundException, IOException {
        logger.info("Method updateAdsPicture was running: {}", idAds);
        Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
        return savePicture(ads, file);
    }

    @Override
    public byte[] getPicture(Long id){
        Optional<Picture> picture = pictureRepository.findById(id);
        if (picture.isPresent()){
            return picture.get().getData();
        } else {
            throw new NotFoundException();
        }
    }

    private Picture savePicture(Ads ads, MultipartFile file) throws IOException {
        logger.info("Method savePicture was running");
        Picture picture = findPictureById(ads.getId());
        try {
            byte[] data = file.getBytes();
            picture.setData(data);
        } catch (IOException e){
            throw new IOException(e);
        }
        picture.setAds(ads);
        picture.setFileSize(file.getSize());
        picture.setMediaType(file.getContentType());
        picture.setFilePath("/api/picture/" + ads.getId().toString());
        return pictureRepository.saveAndFlush(picture);
    }

    private Picture findPictureById(Long id){
        return pictureRepository.findById(id).orElse(new Picture());
    }
}
