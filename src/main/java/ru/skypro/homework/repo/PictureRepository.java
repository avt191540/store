package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Picture;

import java.util.Collection;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Collection<Picture> findAllByAds_Id(Long id);
}
