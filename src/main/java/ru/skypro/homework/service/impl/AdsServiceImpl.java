package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Picture;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repo.AdsRepository;
import ru.skypro.homework.repo.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.PictureService;

import java.io.IOException;
import java.util.Collection;

@Service
public class AdsServiceImpl implements AdsService {

    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final PictureService pictureService;

    private final AdsMapper adsMapper;

    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository, PictureService pictureService,
                          AdsMapper adsMapper) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.adsMapper = adsMapper;
    }

    @Override
    public AdsDto addAds(Authentication auth, CreateAdsDto createAdsDto, MultipartFile file) throws NotFoundException, IOException {
        logger.info("Method addAds is running");

        User user = userRepository.getUserByUsername(auth.getName()).orElseThrow(NotFoundException::new);
        Ads newAds = adsMapper.createAdsDtoToAds(createAdsDto);
        newAds.setUser(user);
        adsRepository.save(newAds);

        Picture picture = pictureService.uploadAdsPicture(newAds.getId(), file);

        newAds.setImage(picture.getFilePath());
        newAds.setPicture(picture);
        adsRepository.save(newAds);
        return adsMapper.adsToAdsDto(newAds);
    }

    @Override
    public ResponseWrapperAds getAllAdsByTitle(String input) {
        Collection<AdsDto> adsDtoCollection = adsMapper.entitiesToDto(adsRepository.findAllByTitleContainsIgnoreCase(input));
        return getWrapperAds(adsDtoCollection);
    }

    @Override
    public ResponseWrapperAds getAdsMeByTitle(String username, String input) {
        Collection<AdsDto> adsDtoCollection = adsMapper.entitiesToDto(adsRepository
                .findAllByUser_UsernameAndTitleContainsIgnoreCase(username, input));
        return getWrapperAds(adsDtoCollection);
    }

    @Override
    public ResponseWrapperAds getAllAds() {
        Collection<AdsDto> adsDtoCollection = adsMapper.entitiesToDto(adsRepository.getAllAds());
        return getWrapperAds(adsDtoCollection);
    }

    @Override
    public ResponseWrapperAds getAdsMe(String username) {
        Collection<AdsDto> adsDtoCollection = adsMapper.entitiesToDto(adsRepository.findAllByUser_Username(username));
        return getWrapperAds(adsDtoCollection);
    }

    @Override
    public void removeAds(Long idAds) throws NotFoundException {
        Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
        adsRepository.delete(ads);
    }

    @Override
    public FullAdsDto getAds(Long idAds) throws NotFoundException {
        Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
        return adsMapper.adsToFullAdsDto(ads, ads.getUser());
    }

    @Override
    public AdsDto updateTextAds(Long idAds, AdsDto adsDto) throws NotFoundException {
        Ads ads = adsRepository.findById(idAds).orElseThrow(NotFoundException::new);
        ads.setTitle(adsDto.getTitle());
        ads.setDescription(adsDto.getDescription());
        ads.setPrice(adsDto.getPrice());
        return adsMapper.adsToAdsDto(adsRepository.save(ads));
    }

    private ResponseWrapperAds getWrapperAds(Collection<AdsDto> collection){
        ResponseWrapperAds wrapperAds = new ResponseWrapperAds();
        wrapperAds.setCount(collection.size());
        wrapperAds.setResults(collection);
        return wrapperAds;
    }
}
