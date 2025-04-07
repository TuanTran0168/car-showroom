package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.*;
import com.tuantran.CarShowroom.mapper.CarMapper;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.repository.CarRepository;
import com.tuantran.CarShowroom.repository.CarTemplateRepository;
import com.tuantran.CarShowroom.repository.FeatureRepository;
import com.tuantran.CarShowroom.repository.FeatureValueRepository;
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
    private CarTemplateRepository carTemplateRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureValueRepository featureValueRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public CarCreateResponse createCar(CarCreateRequest carCreateRequest) {

        if (!carCreateRequest.isValid()) {
            throw new RuntimeException("\"Invalid feature or feature value data. " +
                    "Please ensure that each feature is associated with a valid value, " +
                    "and new features must include new feature values.\"\n");
        }

        if (carCreateRequest.isDuplicateFeature()) {
            throw new RuntimeException("\"Duplicate feature. Please ensure that each feature is unique.\"\n");
        }

        CarTemplate carTemplate = carTemplateRepository.findById(carCreateRequest.getCarTemplateId())
                .orElseThrow(() -> new RuntimeException("Car Template not found"));

        Car car = new Car();
        car.setName(carCreateRequest.getName());
        car.setCarTemplate(carTemplate);

        carCreateRequest.getFeatureForCarCreateRequest().forEach(featureForCar -> {
            // Old feature & old value
            if (featureForCar.getFeatureId() != 0 && featureForCar.getFeatureValueId() != 0) {
                Feature feature = featureRepository.findById(featureForCar.getFeatureId())
                        .orElseThrow(() -> new RuntimeException("Feature not found"));

                FeatureValue featureValue = featureValueRepository.findById(featureForCar.getFeatureValueId())
                        .orElseThrow(() -> new RuntimeException("Feature Value not found"));

                boolean isNotFeatureValueUnique = feature.getFeatureValueList().stream()
                        .allMatch(fv -> fv.getId() != featureValue.getId());

                if (isNotFeatureValueUnique) {
                    throw new RuntimeException("Feature value must be unique for each feature. (" +
                            featureValue.getName() + ") cannot be added to (" + feature.getName() + ")");
                }

                car.getFeatureList().add(feature);
                car.getFeatureValueList().add(featureValue);
            }

            // New feature & new value
            else if (featureForCar.getNewFeature() != null && featureForCar.getNewFeatureValue() != null) {
                Feature newFeature = featureRepository.save(new Feature().builder()
                        .name(featureForCar.getNewFeature().getName())
                        .build());

                FeatureValue newFeatureValue = featureValueRepository.save(new FeatureValue().builder()
                        .name(featureForCar.getNewFeatureValue().getName())
                        .feature(newFeature)
                        .build());

                car.getFeatureList().add(newFeature);
                car.getFeatureValueList().add(newFeatureValue);
            }

            // Old feature & new value
            else if (featureForCar.getFeatureId() != 0 && featureForCar.getNewFeatureValue() != null) {
                Feature feature = featureRepository.findById(featureForCar.getFeatureId())
                        .orElseThrow(() -> new RuntimeException("Feature not found"));

                FeatureValue newFeatureValue = featureValueRepository.save(new FeatureValue().builder()
                        .name(featureForCar.getNewFeatureValue().getName())
                        .feature(feature)
                        .build());

                car.getFeatureList().add(feature);
                car.getFeatureValueList().add(newFeatureValue);
            }
        });
        this.carRepository.save(car);
        return this.carMapper.toCarCreateResponse(car);
    }

    @Override
    public List<CarResponse> findAll() {
        List<Car> carList = carRepository.findAll();
        return this.carMapper.toCarResponseList(carList);
    }

    @Override
    public Page<CarResponse> findAll(Specification<Car> specification, Pageable pageable) {
        return this.carRepository.findAll(specification, pageable).map(carMapper::toCarResponse);
    }
}
