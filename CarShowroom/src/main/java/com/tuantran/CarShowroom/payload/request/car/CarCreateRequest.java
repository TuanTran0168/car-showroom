package com.tuantran.CarShowroom.payload.request.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.NonFinal;

import java.util.List;

@Data
@Builder
public class CarCreateRequest {
    @NotBlank
    private String name;

    @NotNull
    private long carTemplateId;

    List<FeatureForCarCreateRequest> featureForCarCreateRequest;

    @NonFinal
    public boolean isValid() {
        return featureForCarCreateRequest.stream().allMatch(FeatureForCarCreateRequest::isValid);
    }
}
