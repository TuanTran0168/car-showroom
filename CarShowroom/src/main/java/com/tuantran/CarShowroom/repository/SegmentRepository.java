package com.tuantran.CarShowroom.repository;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.Segment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    Optional<Segment> findByName(String name);

    Page<Segment> findByBrand(Brand brand, Pageable pageable);

    Page<Segment> findAll(Pageable pageable);

    Page<Segment> findAll(Specification<Segment> specification, Pageable pageable);

    // Not working with Specification and findBy
    // [org.springframework.dao.InvalidDataAccessApiUsageException:
    // At least 2 parameter(s) provided but only 1 parameter(s) present in query]
    // Page<Segment> findByBrand(Brand brand, Specification<Segment> specification, Pageable pageable);
}
