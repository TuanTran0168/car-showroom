package com.tuantran.CarShowroom.payload.response.car;

import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeatureForCarResponse {
    long count;
    private FeatureCreateResponse featureCreateResponse;
    private List<FeatureValueCreateResponse> featureValueCreateResponse = new ArrayList<>();
}
