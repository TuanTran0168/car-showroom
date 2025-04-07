package com.tuantran.CarShowroom.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @ManyToMany(mappedBy = "featureList", targetEntity = Car.class)
    @ToString.Exclude
    private List<Car> carList;

    @JsonIgnore
    @OneToMany(mappedBy = "feature", targetEntity = FeatureValue.class)
    @ToString.Exclude
    private List<FeatureValue> featureValueList;
}
