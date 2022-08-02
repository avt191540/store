package ru.skypro.homework.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adsComment")
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id")
    private Ads ads;


}
