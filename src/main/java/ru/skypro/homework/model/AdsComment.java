package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment", unique = true)
    private Long id;
    private LocalDateTime createdAt;
    private String text;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Ads ads;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsComment that = (AdsComment) o;
        return id.equals(that.id) && createdAt.equals(that.createdAt) && text.equals(that.text)
                && user.equals(that.user) && ads.equals(that.ads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, text, user, ads);
    }
}
