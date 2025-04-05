package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.entity.FeatureValue;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FeatureValueService {
    List<FeatureValueResponse> findAll();
    Page<FeatureValueResponse> findAll(Specification<FeatureValue> specification, Pageable pageable);
    FeatureValueResponse findById(int id);
    Page<FeatureValueResponse> findByFeature(int featureId, Pageable pageable);
}
