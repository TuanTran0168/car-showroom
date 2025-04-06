package com.tuantran.CarShowroom.service;


import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CarService {
    CarCreateResponse createCar(CarCreateRequest carCreateRequest);

    List<CarResponse> findAll();

    Page<CarResponse> findAll(Specification<Car> specification, Pageable pageable);

    CarResponse findById(long id);
}
