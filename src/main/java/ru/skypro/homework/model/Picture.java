package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_picture", unique = true)
    private Long id;
    private Integer fileSize;
    private String filePath;
    private String mediaType;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Ads ads;

    public Picture() {
    }

    public Picture(Long id, Integer fileSize, String filePath, String mediaType) {
        this.id = id;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.mediaType = mediaType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) && Objects.equals(fileSize, picture.fileSize)
                && Objects.equals(filePath, picture.filePath) && mediaType.equals(picture.mediaType)
                && Arrays.equals(data, picture.data) && ads.equals(picture.ads);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileSize, filePath, mediaType, ads);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", filePath=" + filePath +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", ads=" + ads +
                '}';
    }
}
