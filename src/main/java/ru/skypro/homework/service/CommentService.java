package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;
import ru.skypro.homework.exception.NotFoundException;

public interface CommentService {
    ResponseWrapperAdsComment getAdsComments(Long idAds);

    void deleteCommentToAds(Long idAds, Long id)throws NotFoundException;

    AdsCommentDto getAdsComment(Long idAds, Long id)throws NotFoundException;

    AdsCommentDto addAdsComment(Long idAds, AdsCommentDto adsComment)throws NotFoundException;

    AdsCommentDto updateAdsComment(AdsCommentDto adsComment, Long idAds, Long id)throws NotFoundException;
}
