package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_image_001")
public class Image extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @ManyToOne(targetEntity = CarImage.class)
    @JoinColumn(name = "car_image_id", referencedColumnName = "id", nullable = false)
    private CarImage carImage;
}
