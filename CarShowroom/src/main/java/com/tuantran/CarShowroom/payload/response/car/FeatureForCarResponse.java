package com.tuantran.CarShowroom.payload.response.car;

import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponseForCar;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FeatureForCarResponse {
    long count;
    private FeatureCreateResponse feature;
    private FeatureValueResponseForCar featureValue;
}
