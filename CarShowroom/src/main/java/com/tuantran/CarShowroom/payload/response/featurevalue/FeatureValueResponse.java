package com.tuantran.CarShowroom.payload.response.featurevalue;

import com.tuantran.CarShowroom.payload.response.feature.FeatureForFeatureValueResponse;
import lombok.Data;

import java.util.Date;

@Data
public class FeatureValueResponse {
    private int id;
    private String name;
    private FeatureForFeatureValueResponse feature;
    private Date createdDate;
    private Date updatedDate;
}
