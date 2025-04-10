package com.tuantran.CarShowroom.payload.response.carimage;

import com.tuantran.CarShowroom.payload.response.image.ImageResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CarImageResponse {
    private long id;
//    private long carId;
    private CarForCarImageResponse car;
//    private long colorId;
    private ColorForCarResponse color;
    private List<ImageResponse> images = new ArrayList<>();
    private Date createdDate;
    private Date updatedDate;
}
