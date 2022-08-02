package ru.skypro.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;
    @Column(name = "fileSize")
    private long fileSize;
    @Column(name = "mediaType")
    private String mediaType;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "id")
    private Ads ads;
}
