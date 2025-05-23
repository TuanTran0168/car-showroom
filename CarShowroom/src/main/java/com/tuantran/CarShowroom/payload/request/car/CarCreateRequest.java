package com.tuantran.CarShowroom.payload.request.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarCreateRequest {
    @NotBlank
    private String name;

    @NotNull
    private long carTemplateId;

    List<FeatureForCarCreateRequest> featureForCarCreateRequests;

    @JsonIgnore
    public boolean isValid() {
        return featureForCarCreateRequests.stream().allMatch(FeatureForCarCreateRequest::isValid);
    }

    @JsonIgnore
    public boolean isDuplicateFeature() {
        List<Long> validIds = featureForCarCreateRequests.stream()
                .map(FeatureForCarCreateRequest::getFeatureId)
                .filter(id -> id != 0)
                .toList();

        return validIds.stream().distinct().count() != validIds.size();
    }
}
