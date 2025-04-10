package com.tuantran.CarShowroom.payload.response.carimage;

import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.response.image.ImageCreateResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CarImageCreateResponse {
    private long id;
//    private long carId;
//    private long colorId;
    private CarForCarImageResponse car;
    private ColorForCarResponse color;
    private List<ImageCreateResponse> images = new ArrayList<>();
    private Date createdDate;
    private Date updatedDate;
}
