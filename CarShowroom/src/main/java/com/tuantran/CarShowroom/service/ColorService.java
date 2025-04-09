package com.tuantran.CarShowroom.service;


import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.payload.request.color.ColorCreateRequest;
import com.tuantran.CarShowroom.payload.request.color.ColorUpdateRequest;
import com.tuantran.CarShowroom.payload.response.color.ColorCreateResponse;
import com.tuantran.CarShowroom.payload.response.color.ColorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ColorService {
    List<ColorResponse> findAll();
    Page<ColorResponse> findAll(Pageable pageable);
    Page<ColorResponse> findAll(Specification<Color> specification, Pageable pageable);
    ColorResponse findById(long id);
    ColorCreateResponse createColor(ColorCreateRequest colorCreateRequest);
    ColorResponse updateColor(long id, ColorUpdateRequest colorUpdateRequest);
}
