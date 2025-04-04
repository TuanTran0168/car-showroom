package com.tuantran.CarShowroom.payload.response.brand;

import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BrandResponse {
    private int id;
    private String name;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String imageUrl;
    private String websiteUrl;
    private String description;
    private Set<SegmentResponse> segmentSet;
    private Date createdDate;
    private Date updatedDate;
}
