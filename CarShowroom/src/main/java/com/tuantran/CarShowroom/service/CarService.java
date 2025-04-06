package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;

public interface CarService {
    CarCreateResponse createCar(CarCreateRequest carCreateRequest);
}
