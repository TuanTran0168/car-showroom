package com.tuantran.CarShowroom.repository;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.Segment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface SegmentRepository extends JpaRepository<Segment, Integer> {
    Optional<Segment> findByName(String name);

    Page<Segment> findByBrand(Brand brand, Pageable pageable);

    Page<Segment> findAll(Pageable pageable);
}
