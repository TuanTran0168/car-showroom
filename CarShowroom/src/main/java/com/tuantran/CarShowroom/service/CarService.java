package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.Brand;
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
    Page<CarResponse> findAll(Specification<Brand> specification, Pageable pageable);
}
