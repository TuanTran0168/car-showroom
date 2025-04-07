package com.tuantran.CarShowroom.payload.request.feature;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureCreateRequest {
    private String name;
}
