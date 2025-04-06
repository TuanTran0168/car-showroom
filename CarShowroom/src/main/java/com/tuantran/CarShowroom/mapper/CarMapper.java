package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toCar(CarCreateRequest carCreateRequest);
    CarCreateResponse toCarCreateResponse(Car car);
    CarResponse toCarResponse(Car car);
}
