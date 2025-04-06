package com.tuantran.CarShowroom.payload.response.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CarCreateResponse {
    private String name;
    private long carTemplateId;
    private Set<FeatureForCarResponse> featureForCarResponse = new HashSet<>();
}
