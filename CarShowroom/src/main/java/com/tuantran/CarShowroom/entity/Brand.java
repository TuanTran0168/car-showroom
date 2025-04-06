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
@Table(name = "spring_brand_001")
public class Brand extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country_of_origin", nullable = false)
    private String countryOfOrigin;

    @Column(name = "founded_year", nullable = false)
    private Integer foundedYear;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "website_url")
    private String websiteUrl;

    @Lob
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", targetEntity = Segment.class)
    @ToString.Exclude
    private List<Segment> segmentList;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", targetEntity = CarTemplate.class)
    @ToString.Exclude
    private List<CarTemplate> carTemplateList;

}
