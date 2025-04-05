package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.payload.response.feature.FeatureResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FeatureService {
    List<FeatureResponse> findAll();
    Page<FeatureResponse> findAll(Specification<Feature> specification, Pageable pageable);
    FeatureResponse findById(int id);
}
