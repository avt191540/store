package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "advertisements")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ads", unique = true)
    private Long id;
    private String title;
    private String description;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Collection<Picture> pictures;

    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Collection<AdsComment> adsComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return getId().equals(ads.getId()) && getTitle().equals(ads.getTitle()) && Objects.equals(getDescription(), ads.getDescription()) && Objects.equals(getPrice(), ads.getPrice()) && getUser().equals(ads.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getUser());
    }
}
