package com.tuantran.CarShowroom.repository;

import com.tuantran.CarShowroom.entity.Feature;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findByName(String name);
    Page<Feature> findAll(Specification<Feature> specification, Pageable pageable);
}
