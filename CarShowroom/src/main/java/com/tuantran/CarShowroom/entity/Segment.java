package com.tuantran.CarShowroom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_segment_001")
public class Segment extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "key_feature", nullable = false)
    private String keyFeature;

    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "segment", targetEntity = Car.class)
    @ToString.Exclude
    private Set<Car> carSet;
}
