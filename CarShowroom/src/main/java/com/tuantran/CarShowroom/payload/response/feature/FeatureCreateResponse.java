package com.tuantran.CarShowroom.payload.response.feature;

import lombok.Data;

import java.util.Date;

@Data
public class FeatureCreateResponse {
    private int id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
