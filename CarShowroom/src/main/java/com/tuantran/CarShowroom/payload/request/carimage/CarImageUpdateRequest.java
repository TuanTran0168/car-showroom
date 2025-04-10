package com.tuantran.CarShowroom.payload.request.carimage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarImageUpdateRequest {
    private long carId;
    private long colorId;
    @JsonIgnore
    private List<ImageUpdateRequest> imageUpdateRequests = new ArrayList<>();
    private List<Long> removeImageIds;
}
