package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.PictureDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Picture;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    PictureMapper INSTANCE = Mappers.getMapper(PictureMapper.class);

    @Mapping(source = "ads.id", target = "idAds")
    @Mapping(source = "picture.id", target = "pk")
    @Mapping(source = "picture.fileSize", target = "fileSize")
    @Mapping(source = "picture.mediaType", target = "mediaType")
    PictureDto pictureToPictureDto(Picture picture, Ads ads);
}
