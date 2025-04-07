package com.tuantran.CarShowroom.payload.request.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public boolean isValid() {
        return featureForCarCreateRequest.stream().allMatch(FeatureForCarCreateRequest::isValid);
    }

    @JsonIgnore
    public boolean isDuplicateFeature() {
        List<Long> validIds = featureForCarCreateRequest.stream()
                .map(FeatureForCarCreateRequest::getFeatureId)
                .filter(id -> id != 0)
                .toList();

        return validIds.stream().distinct().count() != validIds.size();
    }
}
