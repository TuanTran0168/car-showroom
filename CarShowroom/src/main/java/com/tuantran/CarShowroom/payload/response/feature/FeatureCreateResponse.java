package com.tuantran.CarShowroom.payload.response.feature;

import lombok.Data;

import java.util.Date;

@Data
public class FeatureCreateResponse {
    private long id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
