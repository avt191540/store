package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(source = "createAdsDto.idAuthor", target = "user.id")
    @Mapping(source = "createAdsDto.title", target = "title")
    @Mapping(source = "createAdsDto.description", target = "description")
    @Mapping(source = "createAdsDto.price", target = "price")
    @Mapping(source = "createAdsDto.image", target = "pictures")
    Ads createAdsDtoToAds(CreateAdsDto createAdsDto, User user);

    @Mapping(source = "ads.title", target = "title")
    @Mapping(source = "ads.price", target = "price")
    @Mapping(source = "user.id", target = "idAuthor")
    @Mapping(source = "ads.id", target = "pk")
    @Mapping(source = "ads.pictures", target = "image")
    AdsDto adsToAdsDto(Ads ads, User user);
}
