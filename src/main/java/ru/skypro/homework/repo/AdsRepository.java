package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Ads;

public interface AdsRepository extends JpaRepository<Ads, Long> {
}
