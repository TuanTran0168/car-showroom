package com.tuantran.CarShowroom.payload.request.carimage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarImageCreateRequest {

    @NotNull
    private long carId;

    @NotNull
    private long colorId;

    @JsonIgnore
    private List<ImageCreateRequest> imageCreateRequests = new ArrayList<>();
}
