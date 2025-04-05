package com.tuantran.CarShowroom.repository;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.entity.FeatureValue;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FeatureValueRepository extends JpaRepository<FeatureValue, Integer> {
    Optional<FeatureValue> findByName(String name);
    Page<FeatureValue> findAll(Specification<FeatureValue> specification, Pageable pageable);
    Optional<FeatureValue> findByNameAndFeature(String name, Feature feature);
}
