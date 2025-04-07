package com.tuantran.CarShowroom.payload.request.featurevalue;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureValueCreateRequest {
    private String name;
}
