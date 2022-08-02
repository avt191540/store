package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ads", unique = true)
    private Long id;
    @Column(name = "authorFirstName")
    private String authorFirstName;
    @Column(name = "authorLastName")
    private String authorLastName;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "price")
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<Picture> pictures;
    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<AdsComment> adsComments;

    public Ads(Long id, String authorFirstName, String authorLastName, String title, String description, String phone, String email, Integer price) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.price = price;
    }

    public Ads() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = pictures;
    }

    public Collection<AdsComment> getAdsComments() {
        return adsComments;
    }

    public void setAdsComments(Collection<AdsComment> adsComments) {
        this.adsComments = adsComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return id.equals(ads.id) && authorFirstName.equals(ads.authorFirstName) && authorLastName.equals(ads.authorLastName) && title.equals(ads.title) && description.equals(ads.description) && phone.equals(ads.phone) && email.equals(ads.email) && price.equals(ads.price) && user.equals(ads.user) && pictures.equals(ads.pictures) && adsComments.equals(ads.adsComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorFirstName, authorLastName, title, description, phone, email, price, user, pictures, adsComments);
    }

    @Override
    public String toString() {
        return "Ads{" +
                "id=" + id +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
