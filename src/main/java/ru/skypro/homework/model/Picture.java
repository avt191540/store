package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @Column(name = "ads_id")
    private Long id;
    private Long fileSize;
    private String filePath;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ads_id")
    //@JsonIgnore
    private Ads ads;


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
}
