package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;

import java.util.Collection;
import java.util.Optional;

public interface AdsService {

    AdsDto addAds(CreateAdsDto createAdsDto);

    Collection<AdsDto> getAllAds();

    Collection<AdsDto> getAdsMe(Long id);

    Collection<AdsCommentDto> getAdsComments(Long ad_pk);

    void deleteCommentToAds(Long ad_pk, Long id);

    AdsCommentDto getAdsComment(Long ad_pk, Long id);

    void removeAds(Long id);

    FullAdsDto getAds(Long id);

    AdsDto updateAds(AdsDto ads, Long id);

    AdsCommentDto addAdsComment(Long ad_pk, AdsCommentDto adsComment);

    AdsCommentDto updateAdsComment(AdsCommentDto adsComment, Long ad_pk, Long id);
}