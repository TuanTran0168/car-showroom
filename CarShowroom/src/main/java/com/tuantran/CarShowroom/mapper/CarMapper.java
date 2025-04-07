package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.entity.FeatureValue;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.payload.response.car.FeatureForCarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.FeatureForCarResponse;
import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponseForCar;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setName(car.getName());
        response.setCarTemplateId(car.getCarTemplate().getId());
        response.setCreatedDate(car.getCreatedDate());
        response.setUpdatedDate(car.getUpdatedDate());

        List<FeatureForCarResponse> featureForCarResponses = new ArrayList<>();
        long count = 1;

        for (Feature feature : car.getFeatureList()) {
            FeatureForCarResponse featureForCarResponse = new FeatureForCarResponse();
            featureForCarResponse.setCount(count++);

            FeatureCreateResponse featureCreateResponse = FeatureCreateResponse.builder()
                    .id(feature.getId())
                    .name(feature.getName())
                    .build();
            featureForCarResponse.setFeature(featureCreateResponse);

            // Tìm feature value tương ứng
            car.getFeatureValueList().stream()
                    .filter(fv -> fv.getFeature().getId().equals(feature.getId()))
                    .findFirst()
                    .ifPresent(fv -> {
                        FeatureValueResponseForCar featureValueResponse = FeatureValueResponseForCar.builder()
                                .id(fv.getId())
                                .name(fv.getName())
                                .build();
                        featureForCarResponse.setFeatureValue(featureValueResponse);
                    });

            featureForCarResponses.add(featureForCarResponse);
        }

        response.setFeatureList(featureForCarResponses);
        return response;
    }

    public List<CarResponse> toCarResponseList(List<Car> cars) {
        return cars.stream()
                .map(this::toCarResponse)
                .collect(Collectors.toList());
    }

    public CarCreateResponse toCarCreateResponse(Car car) {
        CarCreateResponse response = new CarCreateResponse();
        response.setName(car.getName());
        response.setCarTemplateId(car.getCarTemplate().getId());

        List<FeatureForCarCreateResponse> featureList = new ArrayList<>();
        long count = 1;

        for (Feature feature : car.getFeatureList()) {
            FeatureForCarCreateResponse featureForCarCreate = new FeatureForCarCreateResponse();
            featureForCarCreate.setCount(count++);

            FeatureCreateResponse featureCreate = FeatureCreateResponse.builder()
                    .id(feature.getId())
                    .name(feature.getName())
                    .build();
            featureForCarCreate.setFeatureCreateResponse(featureCreate);

            for (FeatureValue featureValue : car.getFeatureValueList()) {
                if (featureValue.getFeature().getId().equals(feature.getId())) {
                    FeatureValueCreateResponse featureValueCreate = FeatureValueCreateResponse.builder()
                            .id(featureValue.getId())
                            .name(featureValue.getName())
                            .build();
                    featureForCarCreate.setFeatureValueCreateResponse(featureValueCreate);
                    break;
                }
            }

            featureList.add(featureForCarCreate);
        }

        response.setFeatureForCarCreateResponse(featureList);
        return response;
    }
}
