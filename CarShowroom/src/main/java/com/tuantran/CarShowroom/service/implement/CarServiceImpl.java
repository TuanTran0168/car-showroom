package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.*;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.payload.response.car.FeatureForCarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.FeatureForCarResponse;
import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponseForCar;
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

import java.util.ArrayList;
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

    @Override
    public CarCreateResponse createCar(CarCreateRequest carCreateRequest) {

        if (!carCreateRequest.isValid()) {
            throw new RuntimeException("\"Invalid feature or feature value data. " +
                    "Please ensure that each feature is associated with a valid value, " +
                    "and new features must include new feature values.\"\n");
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

        CarCreateResponse finalResponse = new CarCreateResponse();
        finalResponse.setName(car.getName());
        finalResponse.setCarTemplateId(car.getCarTemplate().getId());

        final long[] count = {0};
        car.getFeatureList().forEach(feature -> {
            count[0] += 1;
            FeatureForCarCreateResponse featureForCarCreateResponse = new FeatureForCarCreateResponse();
            featureForCarCreateResponse.setCount(count[0]);

            FeatureCreateResponse featureCreateResponse = FeatureCreateResponse.builder()
                    .id(feature.getId())
                    .name(feature.getName())
                    .build();

            featureForCarCreateResponse.setFeatureCreateResponse(featureCreateResponse);

            car.getFeatureValueList().forEach(featureValue -> {
                if (featureValue.getFeature().getId() == feature.getId()) {
                    FeatureValueCreateResponse featureValueCreateResponse = FeatureValueCreateResponse.builder()
                            .id(featureValue.getId())
                            .name(featureValue.getName())
                            .build();

                    featureForCarCreateResponse.setFeatureValueCreateResponse(featureValueCreateResponse);
                }

            });

            finalResponse.getFeatureForCarCreateResponse().add(featureForCarCreateResponse);
        });

        return finalResponse;
    }

    @Override
    public List<CarResponse> findAll() {
        List<Car> carList = carRepository.findAll();
        List<CarResponse> carResponseList = new ArrayList<>();

        carList.forEach(car -> {
            CarResponse carResponse = new CarResponse();
            carResponse.setId(car.getId());
            carResponse.setName(car.getName());
            carResponse.setCarTemplateId(car.getCarTemplate().getId());
            carResponse.setCreatedDate(car.getCreatedDate());
            carResponse.setUpdatedDate(car.getUpdatedDate());

            final long[] count = {0};
            car.getFeatureList().forEach(feature -> {
                count[0] += 1;
                FeatureForCarResponse featureForCarResponse = new FeatureForCarResponse();
                featureForCarResponse.setCount(count[0]);

                FeatureCreateResponse featureCreateResponse = FeatureCreateResponse.builder()
                        .id(feature.getId())
                        .name(feature.getName())
                        .build();

                featureForCarResponse.setFeature(featureCreateResponse);
                car.getFeatureValueList().forEach(featureValue -> {
                    if (featureValue.getFeature().getId() == feature.getId()) {
                        FeatureValueResponseForCar featureValueResponseForCar = FeatureValueResponseForCar.builder()
                                .id(featureValue.getId())
                                .name(featureValue.getName())
                                .build();

                        featureForCarResponse.setFeatureValue(featureValueResponseForCar);
                    }
                });
                carResponse.getFeatureList().add(featureForCarResponse);
            });
            carResponseList.add(carResponse);
        });

        return carResponseList;
    }

    @Override
    public Page<CarResponse> findAll(Specification<Brand> specification, Pageable pageable) {
        return null;
    }
}
