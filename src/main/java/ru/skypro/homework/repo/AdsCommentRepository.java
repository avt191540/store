package ru.skypro.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.model.AdsComment;

import java.util.Collection;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Long> {

    Collection<AdsComment> findAdsCommentsByAds_IdOrderByCreatedAtDesc(Long id);

    @Modifying
    @Query("delete from AdsComment where ads.id = :idAds and id = :idComment")


    int deleteCommentToAdsById(Long idAds, Long idComment);

    boolean existsAdsCommentById(Long id);
}
