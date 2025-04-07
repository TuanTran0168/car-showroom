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
@Table(name = "spring_car_template_001")
public class CarTemplate extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @ManyToOne(targetEntity = Segment.class)
    @JoinColumn(name = "segment_id", referencedColumnName = "id", nullable = false)
    private Segment segment;

    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;

    @JsonIgnore
    @OneToMany(mappedBy = "carTemplate", targetEntity = Car.class)
    @ToString.Exclude
    private List<Car> carList;
}
