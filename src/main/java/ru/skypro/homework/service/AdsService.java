package ru.skypro.homework.service;

import ru.skypro.homework.dto.*;

public interface AdsService {

    AdsDto addAds(CreateAdsDto createAdsDto);

    ResponseWrapperAds getAllAdsByTitle(String input);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAdsMeByTitle(String username, String input);

    ResponseWrapperAds getAdsMe(String username);

    void removeAds(Long id);

    FullAdsDto getAds(Long id);

    AdsDto updateAds(AdsDto adsDto, Long id);
}