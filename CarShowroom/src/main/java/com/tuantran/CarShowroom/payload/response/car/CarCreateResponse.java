package com.tuantran.CarShowroom.payload.response.car;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarCreateResponse {
    private String name;
    private long carTemplateId;
    private List<FeatureForCarCreateResponse> featureForCarCreateResponse = new ArrayList<>();
}
