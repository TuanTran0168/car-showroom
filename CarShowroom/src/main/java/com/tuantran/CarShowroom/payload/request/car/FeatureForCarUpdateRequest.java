package com.tuantran.CarShowroom.payload.request.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuantran.CarShowroom.payload.request.feature.FeatureUpdateRequest;
import com.tuantran.CarShowroom.payload.request.featurevalue.FeatureValueUpdateRequest;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FeatureForCarUpdateRequest {
    private long featureId;
    private long featureValueId;

    private FeatureUpdateRequest newFeature;
    private FeatureValueUpdateRequest newFeatureValue;

    @JsonIgnore
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
