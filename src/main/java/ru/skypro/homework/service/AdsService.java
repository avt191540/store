package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;

import java.util.Collection;

public interface AdsService {

    AdsDto addAds(CreateAdsDto createAdsDto);

    Collection<AdsDto> getAllAds();

    /*Collection<AdsDto> getAllAds(String word);*/

    /*Collection<AdsDto> getAdsMe(Long id, String word);*/
    Collection<AdsDto> getAdsMe(Long id);

    Collection<AdsCommentDto> getAdsComments(Long ad_pk);

    void deleteCommentToAds(Long ad_pk, Long id);

    AdsCommentDto getAdsComment(Long ad_pk, Long id);

    void removeAds(Long id);

    FullAdsDto getAds(Long id);

    AdsDto updateAds(AdsDto adsDto, Long id);

    AdsCommentDto addAdsComment(Long ad_pk, AdsCommentDto adsComment);

    AdsCommentDto updateAdsComment(AdsCommentDto adsComment, Long ad_pk, Long id);
}