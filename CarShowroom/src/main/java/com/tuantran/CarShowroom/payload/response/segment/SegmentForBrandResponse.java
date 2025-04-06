package com.tuantran.CarShowroom.payload.response.segment;

import lombok.Data;

import java.util.Date;

@Data
public class SegmentForBrandResponse {
    private long id;
    private String name;
    private String description;
    private String keyFeature;
}
