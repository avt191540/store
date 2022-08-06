package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;

public interface AdsService {

    AdsDto addAds(CreateAdsDto createAdsDto);
}
