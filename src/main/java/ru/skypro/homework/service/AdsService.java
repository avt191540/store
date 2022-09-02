package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.NotFoundException;

import java.io.IOException;

public interface AdsService {

    AdsDto addAds(Authentication auth, CreateAdsDto createAdsDto, MultipartFile file) throws NotFoundException, IOException;

    ResponseWrapperAds getAllAdsByTitle(String input);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAdsMeByTitle(String username, String input);

    ResponseWrapperAds getAdsMe(String username);

    void removeAds(Long id) throws NotFoundException;

    FullAdsDto getAds(Long idAds) throws NotFoundException;

    AdsDto updateTextAds(Long id, AdsDto adsDto) throws NotFoundException;
}