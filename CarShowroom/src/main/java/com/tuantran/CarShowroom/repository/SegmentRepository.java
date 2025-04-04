package com.tuantran.CarShowroom.repository;


import com.tuantran.CarShowroom.entity.Segment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface SegmentRepository extends JpaRepository<Segment, Integer> {
    Optional<Segment> findByName(String name);
}
