package com.tuantran.CarShowroom.payload.response.brand;

import lombok.Data;

@Data
public class BrandForCarResponse {
    private long id;
    private String name;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String imageUrl;
    private String websiteUrl;
    private String description;
}
