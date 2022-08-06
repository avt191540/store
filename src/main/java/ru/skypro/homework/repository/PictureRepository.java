package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
