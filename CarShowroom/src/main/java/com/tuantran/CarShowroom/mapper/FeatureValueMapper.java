package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.FeatureValue;
import com.tuantran.CarShowroom.payload.request.featurevalue.FeatureValueCreateRequest;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueCreateResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureValueMapper {
    FeatureValue toFeatureValue(FeatureValueCreateRequest featureValueCreateRequest);
    FeatureValueCreateResponse toFeatureValueCreateResponse(FeatureValue featureValue);
    @Mapping(source = "feature", target = "feature")
    FeatureValueResponse toFeatureValueResponse(FeatureValue featureValue);
}
