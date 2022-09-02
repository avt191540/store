package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Picture;

import java.io.IOException;

public interface PictureService {
    Picture uploadAdsPicture(Long idAds, MultipartFile file) throws NotFoundException, IOException;

    Picture updateAdsPicture(Long idAds, MultipartFile file) throws NotFoundException, IOException;

    public byte[] getPicture(Long id);
}
