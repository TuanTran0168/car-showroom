package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarImageMapperCustom {

    @Autowired
    CarImageMapper carImageMapper;

    @Autowired
    ImageMapper imageMapper;

    public CarImageCreateResponse toCarImageCreateResponse(CarImage carImage) {
        CarImageCreateResponse carImageCreateResponse = this.carImageMapper.toCarImageCreateResponse(carImage);
        carImage.getImageList().forEach(image -> {
            ImageCreateResponse imageCreateResponse = this.imageMapper.toImageCreateResponse(image);
            carImageCreateResponse.getImages().add(imageCreateResponse);
        });
        return carImageCreateResponse;
    }

    public CarImageResponse toCarImageResponse(CarImage carImage) {
        CarImageResponse carImageResponse = this.carImageMapper.toCarImageResponse(carImage);
        carImage.getImageList().forEach(image -> {
            ImageResponse imageResponse = this.imageMapper.toImageResponse(image);
            carImageResponse.getImages().add(imageResponse);
        });
        return carImageResponse;
    }
}
