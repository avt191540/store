package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.model.AdsComment;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Optional;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Long> {

    @Query(value = "select * from comments where id_ads = :id", nativeQuery = true)
    Optional<Collection<AdsComment>> getAllCommentsByAdsId(Long id);

    @Modifying
    @Query(value = "delete from comments where id_ads = :ad_pk and id_comment = :id", nativeQuery = true)
    int deleteCommentToAdsById(Long ad_pk, Long id);

    @Query(value = "select * from comments where id_ads = :ad_pk and id_comment = :id", nativeQuery = true)
    Optional<AdsComment> getCommentToAdsById(Long ad_pk, Long id);

    boolean existsAdsCommentsByIdAndAds_Id(Long ad_pk, Long id);

    @Query(value = "select * from users u inner join comments c on u.id_user = c.id_user and c.id_user = :id ", nativeQuery = true)
    Optional<User> getUserByCommentId(Long id);
}
