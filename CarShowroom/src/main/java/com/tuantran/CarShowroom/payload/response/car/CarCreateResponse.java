package com.tuantran.CarShowroom.payload.response.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarCreateResponse {
    private String name;
    private long carTemplateId;
    private List<FeatureForCarResponse> featureForCarResponse = new ArrayList<>();
}
