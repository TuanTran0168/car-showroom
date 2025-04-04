package com.tuantran.CarShowroom.payload.request.segment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SegmentUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
