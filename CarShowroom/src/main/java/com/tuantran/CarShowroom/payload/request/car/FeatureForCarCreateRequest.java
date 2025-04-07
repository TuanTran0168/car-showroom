package com.tuantran.CarShowroom.payload.request.car;

import com.tuantran.CarShowroom.payload.request.feature.FeatureCreateRequest;
import com.tuantran.CarShowroom.payload.request.featurevalue.FeatureValueCreateRequest;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.NonFinal;


@Data
@Builder
public class FeatureForCarCreateRequest {
    private long featureId;
    private long featureValueId;

    private FeatureCreateRequest newFeature;
    private FeatureValueCreateRequest newFeatureValue;

    @NonFinal
    public boolean isValid() {
       // New feature & old value  => false
        if (newFeature != null && featureValueId != 0) {
            return false;
        }

        // New feature & new value  => true
        // Old feature & old value  => true
        // Old feature & new value  => true
        return true;
    }
}
