package com.tuantran.CarShowroom.payload.request.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarCreateRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private long segmentId;

    @NotNull
    private long brandId;

    @NotNull
    private long typeId;
}
