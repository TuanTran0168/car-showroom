package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarImageMapper {
    CarImage toCarImage(CarImageCreateRequest carImageCreateRequest);

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "color.id", target = "colorId")
    CarImageCreateResponse toCarImageCreateResponse(CarImage carImage);

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "color.id", target = "colorId")
    CarImageResponse toCarImageResponse(CarImage carImage);
}
