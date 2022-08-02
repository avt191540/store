package ru.skypro.homework.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ads_Comment")
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adsComment", unique = true)
    private Long id;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Ads ads;

    public AdsComment(Long id, LocalDateTime createdAt, String text, User user, Ads ads) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
        this.user = user;
        this.ads = ads;
    }

    public AdsComment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsComment that = (AdsComment) o;
        return id.equals(that.id) && createdAt.equals(that.createdAt) && text.equals(that.text) && user.equals(that.user) && ads.equals(that.ads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, text, user, ads);
    }

    @Override
    public String toString() {
        return "AdsComment{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }
}
