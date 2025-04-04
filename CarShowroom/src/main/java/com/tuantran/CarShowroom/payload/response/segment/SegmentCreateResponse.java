package com.tuantran.CarShowroom.payload.response.segment;

import lombok.Data;

import java.util.Date;

@Data
public class SegmentCreateResponse {
    private int id;
    private String name;
    private String description;
    private String keyFeature;
    private Date createdDate;
    private Date updatedDate;
}
