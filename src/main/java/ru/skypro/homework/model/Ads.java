package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
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
    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<Picture> pictures;
    @OneToMany(mappedBy = "ads", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<AdsComment> adsComments;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


}
