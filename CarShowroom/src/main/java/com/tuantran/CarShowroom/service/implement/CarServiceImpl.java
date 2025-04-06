package com.tuantran.CarShowroom.service.implement;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.entity.Type;
import com.tuantran.CarShowroom.mapper.CarMapper;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.repository.BrandRepository;
import com.tuantran.CarShowroom.repository.CarRepository;
import com.tuantran.CarShowroom.repository.SegmentRepository;
import com.tuantran.CarShowroom.repository.TypeRepository;
import com.tuantran.CarShowroom.service.CarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Override
    public CarCreateResponse createCar(CarCreateRequest carCreateRequest) {

        Car car = carMapper.toCar(carCreateRequest);
        Brand brand = brandRepository.findById(carCreateRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        car.setBrand(brand);

        Type type = typeRepository.findById(carCreateRequest.getTypeId())
                .orElseThrow(() -> new RuntimeException("Type not found"));
        car.setType(type);

        Segment segment = segmentRepository.findById(carCreateRequest.getSegmentId())
                .orElseThrow(() -> new RuntimeException("Segment not found"));
        car.setSegment(segment);
        return carMapper.toCarCreateResponse(this.carRepository.save(car));
    }

    @Override
    public List<CarResponse> findAll() {
        return this.carRepository.findAll().stream().map(carMapper::toCarResponse).toList();
    }

    @Override
    public Page<CarResponse> findAll(Specification<Car> specification, Pageable pageable) {
        return this.carRepository.findAll(specification, pageable).map(carMapper::toCarResponse);
    }

    @Override
    public CarResponse findById(long id) {
        return this.carRepository.findById(id).map(carMapper::toCarResponse)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }
}
