package com.tuantran.CarShowroom.payload.response.brand;

import com.tuantran.CarShowroom.payload.response.segment.SegmentForBrandResponse;
import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BrandResponse {
    private long id;
    private String name;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String imageUrl;
    private String websiteUrl;
    private String description;
    private List<SegmentForBrandResponse> segmentList;
    private Date createdDate;
    private Date updatedDate;
}
