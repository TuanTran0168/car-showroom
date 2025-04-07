package com.tuantran.CarShowroom.payload.response.segment;

import lombok.Data;

@Data
public class SegmentForCarResponse {
    private long id;
    private String name;
    private String description;
    private String keyFeature;
}
