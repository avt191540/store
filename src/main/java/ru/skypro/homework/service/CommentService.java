package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;

public interface CommentService {
    ResponseWrapperAdsComment getAdsComments(Long idAds);

    void deleteCommentToAds(Long idAds, Long id);

    AdsCommentDto getAdsComment(Long idAds, Long id);

    AdsCommentDto addAdsComment(Long idAds, AdsCommentDto adsComment);

    AdsCommentDto updateAdsComment(AdsCommentDto adsComment, Long idAds, Long id);
}
