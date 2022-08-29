package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdsCommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsComment;
import ru.skypro.homework.repo.AdsCommentRepository;
import ru.skypro.homework.repo.AdsRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {

    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final AdsCommentRepository commentRepository;

    private final AdsRepository adsRepository;

    private final AdsCommentMapper commentMapper;

    public CommentServiceImpl(AdsCommentRepository commentRepository, AdsRepository adsRepository, AdsCommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public ResponseWrapperAdsComment getAdsComments(Long id) {
        logger.info("Method getAdsComment was started: {}", id);
        Collection<AdsCommentDto> adsComments = commentMapper.entitiesToDto(commentRepository
                .findAdsCommentsByAds_IdOrderByCreatedAtDesc(id));
        ResponseWrapperAdsComment wrapperAdsComment = new ResponseWrapperAdsComment();
        wrapperAdsComment.setCount(adsComments.size());
        wrapperAdsComment.setResults(adsComments);
        return wrapperAdsComment;
    }

    @Override
    @Transactional
    public void deleteCommentToAds(Long idAds, Long id) throws NotFoundException {
        if(commentRepository.deleteCommentToAdsById(idAds, id) != 1){
            throw new NotFoundException();
        }
    }

    @Override
    public AdsCommentDto getAdsComment(Long idAds, Long id) throws NotFoundException {
        AdsComment foundComment = commentRepository.findAdsCommentByIdAndAds_Id(idAds, id).orElseThrow(NotFoundException::new);
        return commentMapper.entityToDto(foundComment);
    }

    @Override
    public AdsCommentDto addAdsComment(Long idAds, AdsCommentDto adsComment) throws NotFoundException {
        logger.info("Method addAdsComment was started: {} {}", idAds, adsComment);
        Ads foundAds = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);

        AdsComment newComment = commentMapper.adsCommentDtoToEntity(adsComment, foundAds);
        newComment.setAds(foundAds);
        commentRepository.save(newComment);

        return commentMapper.entityToDto(newComment);
    }

    @Override
    public AdsCommentDto updateAdsComment(AdsCommentDto adsCommentDto, Long idAds, Long id) throws NotFoundException {
        logger.info("Method updateAdsComment was started: {} {} {}", adsCommentDto, idAds, id);
        if (commentRepository.existsAdsCommentById(id)) {
            Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
            AdsComment commentUpdate = commentMapper.adsCommentDtoToEntity(adsCommentDto, ads);
            commentRepository.save(commentUpdate);
            return commentMapper.entityToDto(commentUpdate);
        }
        throw new NotFoundException();
    }
}
