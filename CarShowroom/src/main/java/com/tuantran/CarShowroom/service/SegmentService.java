package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SegmentService {
    Page<SegmentResponse> findByBrand(int brandId, Pageable pageable);
    List<SegmentResponse> findAll();
    Page<SegmentResponse> findAll(Pageable pageable);
    SegmentResponse findById(int id);
}
