package com.tuantran.CarShowroom.payload.response.featurevalue;

import lombok.Data;

import java.util.Date;

@Data
public class FeatureValueCreateResponse {
    private int id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
