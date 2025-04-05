package com.tuantran.CarShowroom.payload.response.feature;

import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueForFeatureResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FeatureResponse {
    private int id;
    private String name;
    private List<FeatureValueForFeatureResponse> featureValueList;
    private Date createdDate;
    private Date updatedDate;
}
