package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repo.AdsRepository;
import ru.skypro.homework.repo.UserRepository;
import ru.skypro.homework.service.AdsService;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdsServiceImpl implements AdsService {

    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsMapper adsMapper;

    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository, AdsMapper adsMapper) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.adsMapper = adsMapper;
    }

    @Override
    public AdsDto addAds(CreateAdsDto createAdsDto) {
        logger.info("Method addAds is running");
        User user = userRepository.findById(createAdsDto.getIdAuthor()).get();
        Ads newAds = adsMapper.createAdsDtoToAds(createAdsDto, user);
        adsRepository.save(newAds);
        return adsMapper.adsToAdsDto(newAds, user);
    }
}
