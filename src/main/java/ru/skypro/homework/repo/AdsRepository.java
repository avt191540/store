package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;

import java.util.Collection;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {

    Collection<Ads> findAllByUserIdAndTitleContainsIgnoreCase(Long id, String input);

    Collection<Ads> findAllByUserId(Long id);

    Collection<Ads> findAllByTitleContainsIgnoreCase(String input);
}
