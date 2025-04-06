package com.tuantran.CarShowroom.payload.response.car;

import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FeatureForCarResponse {
    long count;
    private FeatureCreateResponse featureCreateResponse;
    private Set<FeatureValueCreateResponse> featureValueCreateResponse = new HashSet<>();
}
