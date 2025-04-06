package com.tuantran.CarShowroom.payload.response.segment;

import lombok.Data;

import java.util.Date;

@Data
public class SegmentCreateResponse {
    private long id;
    private String name;
    private String description;
    private String keyFeature;
    private Date createdDate;
    private Date updatedDate;
}
