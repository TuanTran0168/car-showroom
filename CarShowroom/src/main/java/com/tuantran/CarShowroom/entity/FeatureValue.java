package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_feature_value_001")
public class FeatureValue extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = Feature.class)
    @JoinColumn(name = "feature_id", referencedColumnName = "id", nullable = false)
    private Feature feature;

    @ManyToOne(targetEntity = Variant.class)
    @JoinColumn(name = "variant_id", referencedColumnName = "id")
    private Variant variant;
}
