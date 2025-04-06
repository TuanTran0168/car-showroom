package com.tuantran.CarShowroom.service;


import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateCreateRequest;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateUpdateRequest;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateCreateResponse;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CarTemplateService {
    CarTemplateCreateResponse createCarTemplate(CarTemplateCreateRequest carTemplateCreateRequest);

    List<CarTemplateResponse> findAll();

    Page<CarTemplateResponse> findAll(Specification<CarTemplate> specification, Pageable pageable);

    CarTemplateResponse findById(long id);

    CarTemplateResponse updateCarTemplate(long id, CarTemplateUpdateRequest carTemplateUpdateRequest);
}
