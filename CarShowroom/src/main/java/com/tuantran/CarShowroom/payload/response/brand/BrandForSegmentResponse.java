package com.tuantran.CarShowroom.payload.response.brand;

import lombok.Data;

import java.util.Date;

@Data
public class BrandForSegmentResponse {
    private int id;
    private String name;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String imageUrl;
    private String websiteUrl;
    private String description;
}
