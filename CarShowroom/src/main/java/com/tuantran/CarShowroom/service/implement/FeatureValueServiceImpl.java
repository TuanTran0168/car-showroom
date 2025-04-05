package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.entity.FeatureValue;
import com.tuantran.CarShowroom.mapper.FeatureValueMapper;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponse;
import com.tuantran.CarShowroom.repository.FeatureRepository;
import com.tuantran.CarShowroom.repository.FeatureValueRepository;
import com.tuantran.CarShowroom.service.FeatureValueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FeatureValueServiceImpl implements FeatureValueService {

    @Autowired
    private FeatureValueRepository featureValueRepository;

    @Autowired
    private FeatureValueMapper featureValueMapper;

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public List<FeatureValueResponse> findAll() {
        return this.featureValueRepository.findAll().stream().map(featureValueMapper::toFeatureValueResponse).toList();
    }

    @Override
    public Page<FeatureValueResponse> findAll(Specification<FeatureValue> specification, Pageable pageable) {
        return this.featureValueRepository.findAll(specification, pageable).map(featureValueMapper::toFeatureValueResponse);
    }

    @Override
    public FeatureValueResponse findById(int id) {
        return this.featureValueRepository.findById(id).map(featureValueMapper::toFeatureValueResponse)
                .orElseThrow(() -> new RuntimeException("Feature Value not found"));
    }

    @Override
    public Page<FeatureValueResponse> findByFeature(int featureId, Pageable pageable) {
        Feature feature = this.featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        return this.featureValueRepository.findByFeature(feature, pageable).map(featureValueMapper::toFeatureValueResponse);
    }
}
