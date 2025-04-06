package com.tuantran.CarShowroom.payload.response.featurevalue;

import lombok.Data;

import java.util.Date;

@Data
public class FeatureValueCreateResponse {
    private long id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
