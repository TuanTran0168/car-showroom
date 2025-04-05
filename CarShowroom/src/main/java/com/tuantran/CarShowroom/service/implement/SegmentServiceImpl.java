package com.tuantran.CarShowroom.service.implement;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.mapper.SegmentMapper;
import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import com.tuantran.CarShowroom.repository.BrandRepository;
import com.tuantran.CarShowroom.repository.SegmentRepository;
import com.tuantran.CarShowroom.service.SegmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SegmentServiceImpl implements SegmentService {

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SegmentMapper segmentMapper;

    @Override
    public Page<SegmentResponse> findByBrand(int brandId, Pageable pageable) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return segmentRepository.findByBrand(brand, pageable).map(segmentMapper::toSegmentResponse);
    }

    @Override
    public List<SegmentResponse> findAll() {
        return segmentRepository.findAll().stream().map(segmentMapper::toSegmentResponse).toList();
    }

    @Override
    public Page<SegmentResponse> findAll(Pageable pageable) {
        return segmentRepository.findAll(pageable).map(segmentMapper::toSegmentResponse);
    }

    @Override
    public SegmentResponse findById(int id) {
        return segmentMapper.toSegmentResponse(segmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segment not found")));
    }

    @Override
    public Page<SegmentResponse> findAll(Specification<Segment> specification, Pageable pageable) {
        return segmentRepository.findAll(specification, pageable).map(segmentMapper::toSegmentResponse);
    }

    @Override
    public Page<SegmentResponse> findByBrand(int brandId, Specification<Segment> specification, Pageable pageable) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return this.segmentRepository.findByBrand(brand, pageable).map(segmentMapper::toSegmentResponse);
    }

}
