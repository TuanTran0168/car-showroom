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
@Table(name = "spring_feature_value_001")
public class FeatureValue extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = Feature.class)
    @JoinColumn(name = "feature_id", referencedColumnName = "id", nullable = false)
    private Feature feature;

    @JsonIgnore
    @ManyToMany(mappedBy = "featureValueSet", targetEntity = Car.class)
    @ToString.Exclude
    private Set<Car> carSet;
}
