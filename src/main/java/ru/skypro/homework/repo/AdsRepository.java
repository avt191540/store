package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {
    Optional<Collection<Ads>> findAllById(Long id);

    //Optional<Collection<Ads>> getAll();

    int deleteAdsById(Long id);

    @Query(value = "select * from users u inner join advertisements a on u.id_user = a.id_user and a.id_ads = :id", nativeQuery = true)
    Optional<User> getUserByAdsId(Long id);
}
