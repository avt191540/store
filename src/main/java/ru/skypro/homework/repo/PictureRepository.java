package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
