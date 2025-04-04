package com.tuantran.CarShowroom.service;


import com.tuantran.CarShowroom.payload.request.type.TypeCreateRequest;
import com.tuantran.CarShowroom.payload.request.type.TypeUpdateRequest;
import com.tuantran.CarShowroom.payload.response.type.TypeCreateResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    List<TypeResponse> findAll();
    Page<TypeResponse> findAll(Pageable pageable);
    TypeCreateResponse createType(TypeCreateRequest typeCreateRequest);
    TypeResponse updateType(int id, TypeUpdateRequest typeUpdateRequest);
    TypeResponse findById(int id);
}
