package com.tuantran.CarShowroom.payload.request.feature;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureUpdateRequest {
    private String name;
}
