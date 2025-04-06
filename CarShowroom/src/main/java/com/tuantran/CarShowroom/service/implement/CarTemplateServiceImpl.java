package com.tuantran.CarShowroom.service.implement;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.entity.Type;
import com.tuantran.CarShowroom.mapper.CarTemplateMapper;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateCreateRequest;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateUpdateRequest;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateCreateResponse;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateResponse;
import com.tuantran.CarShowroom.repository.BrandRepository;
import com.tuantran.CarShowroom.repository.CarTemplateRepository;
import com.tuantran.CarShowroom.repository.SegmentRepository;
import com.tuantran.CarShowroom.repository.TypeRepository;
import com.tuantran.CarShowroom.service.CarTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarTemplateServiceImpl implements CarTemplateService {

    @Autowired
    private CarTemplateRepository carTemplateRepository;

    @Autowired
    private CarTemplateMapper carTemplateMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Override
    public CarTemplateCreateResponse createCarTemplate(CarTemplateCreateRequest carTemplateCreateRequest) {

        CarTemplate carTemplate = carTemplateMapper.toCarTemplate(carTemplateCreateRequest);
        Brand brand = brandRepository.findById(carTemplateCreateRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        carTemplate.setBrand(brand);

        Type type = typeRepository.findById(carTemplateCreateRequest.getTypeId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        carTemplate.setType(type);

        Segment segment = segmentRepository.findById(carTemplateCreateRequest.getSegmentId())
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        carTemplate.setSegment(segment);
        return carTemplateMapper.toCarTemplateCreateResponse(this.carTemplateRepository.save(carTemplate));
    }

    @Override
    public List<CarTemplateResponse> findAll() {
        return this.carTemplateRepository.findAll().stream().map(carTemplateMapper::toCarTemplateResponse).toList();
    }

    @Override
    public Page<CarTemplateResponse> findAll(Specification<CarTemplate> specification, Pageable pageable) {
        return this.carTemplateRepository.findAll(specification, pageable).map(carTemplateMapper::toCarTemplateResponse);
    }

    @Override
    public CarTemplateResponse findById(long id) {
        return this.carTemplateRepository.findById(id).map(carTemplateMapper::toCarTemplateResponse)
                .orElseThrow(() -> new RuntimeException("Car Template not found"));
    }

    @Override
    public CarTemplateResponse updateCarTemplate(long id, CarTemplateUpdateRequest carTemplateUpdateRequest) {
        CarTemplate carTemplate = this.carTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car Template not found"));

        this.carTemplateMapper.updateCarTemplate(carTemplate, carTemplateUpdateRequest);
        carTemplate.setBrand(brandRepository.findById(carTemplateUpdateRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));

        carTemplate.setType(typeRepository.findById(carTemplateUpdateRequest.getTypeId())
                .orElseThrow(() -> new RuntimeException("Type not found")));

        carTemplate.setSegment(segmentRepository.findById(carTemplateUpdateRequest.getSegmentId())
                .orElseThrow(() -> new RuntimeException("Segment not found")));
        return carTemplateMapper.toCarTemplateResponse(this.carTemplateRepository.save(carTemplate));
    }
}
