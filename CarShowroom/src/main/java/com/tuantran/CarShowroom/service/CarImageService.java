package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageUpdateRequest;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarImageService {
    CarImageCreateResponse createCarImage(CarImageCreateRequest carImageCreateRequest);
    CarImageResponse updateCarImage(long id, CarImageUpdateRequest carImageUpdateRequest);
    List<CarImageResponse> findAll();
    Page<CarImageResponse> findAll(Specification<CarImage> specification, Pageable pageable);
}
