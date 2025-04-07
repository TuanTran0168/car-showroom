package com.tuantran.CarShowroom.payload.response.segment;

import com.tuantran.CarShowroom.payload.response.brand.BrandForSegmentResponse;
import lombok.Data;

import java.util.Date;

@Data
public class SegmentResponse {
    private long id;
    private String name;
    private String description;
    private String keyFeature;
    private BrandForSegmentResponse brand;
    private Date createdDate;
    private Date updatedDate;
}
