package com.tuantran.CarShowroom.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_feature_001")
public class Feature extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "featureSet", targetEntity = Car.class)
    @ToString.Exclude
    private Set<Car> carSet;

    @JsonIgnore
    @OneToMany(mappedBy = "feature", targetEntity = FeatureValue.class)
    @ToString.Exclude
    private List<FeatureValue> featureValueList;
}
