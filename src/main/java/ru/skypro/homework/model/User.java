package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<Ads> ads;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    @JsonIgnore
    private Collection<AdsComment> adsComment;

}
