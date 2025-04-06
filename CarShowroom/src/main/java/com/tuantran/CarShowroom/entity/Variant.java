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
@Table(name = "spring_variant_001")
public class Variant extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = CarTemplate.class)
    @JoinColumn(name = "car_template_id", referencedColumnName = "id", nullable = false)
    private CarTemplate carTemplate;

    @JsonIgnore
    @ManyToMany(targetEntity = Feature.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "spring_variant_feature_001",
            joinColumns = @JoinColumn(name = "variant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Feature> featureSet;

    @JsonIgnore
    @ManyToMany(targetEntity = FeatureValue.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "spring_variant_feature_value_001",
            joinColumns = @JoinColumn(name = "variant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "feature_value_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<FeatureValue> featureValueSet;
}
