package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.mapper.FeatureMapper;
import com.tuantran.CarShowroom.payload.response.feature.FeatureResponse;
import com.tuantran.CarShowroom.repository.FeatureRepository;
import com.tuantran.CarShowroom.service.FeatureService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureMapper featureMapper;

    @Override
    public List<FeatureResponse> findAll() {
        return this.featureRepository.findAll().stream().map(featureMapper::toFeatureResponse).toList();
    }

    @Override
    public Page<FeatureResponse> findAll(Specification<Feature> specification, Pageable pageable) {
        return this.featureRepository.findAll(specification, pageable).map(featureMapper::toFeatureResponse);
    }

    @Override
    public FeatureResponse findById(long id) {
        return this.featureRepository.findById(id).map(featureMapper::toFeatureResponse)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
    }
}
