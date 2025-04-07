package com.tuantran.CarShowroom.payload.response.featurevalue;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FeatureValueResponseForCar {
    private long id;
    private String name;
}
