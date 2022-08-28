package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {

    Optional<Collection<Ads>> findAllByUserIdAndTitleContainsIgnoreCase(Long id, String input);

    Optional<Collection<Ads>> findAllByUserId(Long id);

    Optional<Collection<Ads>> findAllByTitleContainsIgnoreCase(String input);
}
