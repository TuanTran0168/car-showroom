package com.tuantran.CarShowroom.payload.request.cartemplate;

import com.tuantran.CarShowroom.entity.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarTemplateCreateRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private long brandId;

    @NotNull
    private long segmentId;

    @NotNull
    private long typeId;
}
