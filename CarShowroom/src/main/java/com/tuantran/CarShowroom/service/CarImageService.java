package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageUpdateRequest;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import org.springframework.stereotype.Service;

@Service
public interface CarImageService {
    CarImageCreateResponse createCarImage(CarImageCreateRequest carImageCreateRequest);
    CarImageResponse updateCarImage(long id, CarImageUpdateRequest carImageUpdateRequest);
}
