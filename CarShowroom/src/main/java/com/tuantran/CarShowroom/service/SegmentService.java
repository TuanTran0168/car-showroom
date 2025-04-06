package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SegmentService {
    Page<SegmentResponse> findByBrand(long brandId, Pageable pageable);
    List<SegmentResponse> findAll();
    Page<SegmentResponse> findAll(Pageable pageable);
    SegmentResponse findById(long id);
    Page<SegmentResponse> findAll(Specification<Segment> specification, Pageable pageable);
    Page<SegmentResponse> findByBrand(long brandId, Specification<Segment> specification, Pageable pageable);
}
