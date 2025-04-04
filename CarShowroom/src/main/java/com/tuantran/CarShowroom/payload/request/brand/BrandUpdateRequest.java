package com.tuantran.CarShowroom.payload.request.brand;

import lombok.Data;

@Data
public class BrandUpdateRequest {
    private String name;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String imageUrl;
    private String websiteUrl;
    private String description;
}
