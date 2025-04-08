package com.tuantran.CarShowroom.payload.request.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarUpdateRequest {
    private String name;
    private long carTemplateId;
    List<FeatureForCarUpdateRequest> featureForCarUpdateRequests;
    @JsonIgnore
    public boolean isValid() {
        return featureForCarUpdateRequests.stream().allMatch(FeatureForCarUpdateRequest::isValid);
    }

    @JsonIgnore
    public boolean isDuplicateFeature() {
        List<Long> validIds = featureForCarUpdateRequests.stream()
                .map(FeatureForCarUpdateRequest::getFeatureId)
                .filter(id -> id != 0)
                .toList();

        return validIds.stream().distinct().count() != validIds.size();
    }
}
