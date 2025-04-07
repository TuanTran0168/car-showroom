package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.entity.FeatureValue;
import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.FeatureForCarResponse;
import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import com.tuantran.CarShowroom.repository.CarRepository;
import com.tuantran.CarShowroom.repository.CarTemplateRepository;
import com.tuantran.CarShowroom.repository.FeatureRepository;
import com.tuantran.CarShowroom.repository.FeatureValueRepository;
import com.tuantran.CarShowroom.service.CarService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
                    throw new RuntimeException("Feature value must be unique for each feature.");
                }

                car.getFeatureSet().add(feature);
                car.getFeatureValueSet().add(featureValue);

//                Set<Feature> existingFeatures = new HashSet<>(car.getFeatureSet());
//                Set<FeatureValue> existingFeatureValues = new HashSet<>(car.getFeatureValueSet());
//
//                existingFeatures.add(feature);
//                existingFeatureValues.add(featureValue);
//
//                car.setFeatureSet(existingFeatures);
//                car.setFeatureValueSet(existingFeatureValues);
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

                car.getFeatureSet().add(newFeature);
                car.getFeatureValueSet().add(newFeatureValue);
            }

            // Old feature & new value
            else if (featureForCar.getFeatureId() != 0 && featureForCar.getNewFeatureValue() != null) {
                Feature feature = featureRepository.findById(featureForCar.getFeatureId())
                        .orElseThrow(() -> new RuntimeException("Feature not found"));

                FeatureValue newFeatureValue = featureValueRepository.save(new FeatureValue().builder()
                        .name(featureForCar.getNewFeatureValue().getName())
                        .feature(feature)
                        .build());

                car.getFeatureSet().add(feature);
                car.getFeatureValueSet().add(newFeatureValue);
            }
            this.carRepository.save(car);
        });


        CarCreateResponse finalResponse = new CarCreateResponse();
        finalResponse.setName(car.getName());
        finalResponse.setCarTemplateId(car.getCarTemplate().getId());

        final long[] count = {0};
        car.getFeatureSet().forEach(feature -> {
            count[0] += 1;
            FeatureForCarResponse featureForCarResponse = new FeatureForCarResponse();
            featureForCarResponse.setCount(count[0]);

            FeatureCreateResponse featureCreateResponse = FeatureCreateResponse.builder()
                    .id(feature.getId())
                    .name(feature.getName())
                    .createdDate(feature.getCreatedDate())
                    .updatedDate(feature.getUpdatedDate())
                    .build();

            featureForCarResponse.setFeatureCreateResponse(featureCreateResponse);

            car.getFeatureValueSet().forEach(featureValue -> {
                if (featureValue.getFeature().getId() == feature.getId()) {
                    FeatureValueCreateResponse featureValueCreateResponse = FeatureValueCreateResponse.builder()
                            .id(featureValue.getId())
                            .name(featureValue.getName())
                            .createdDate(featureValue.getCreatedDate())
                            .updatedDate(featureValue.getUpdatedDate())
                            .build();

                    featureForCarResponse.getFeatureValueCreateResponse().add(featureValueCreateResponse);
                }

            });

            finalResponse.getFeatureForCarResponse().add(featureForCarResponse);
        });

        return finalResponse;
    }
}
