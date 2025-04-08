package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_car_image_001")
public class CarImage extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    private Car car;

    @ManyToOne(targetEntity = Color.class)
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private Color color;
}
