package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.payload.request.feature.FeatureCreateRequest;
import com.tuantran.CarShowroom.payload.response.feature.FeatureCreateResponse;
import com.tuantran.CarShowroom.payload.response.feature.FeatureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    Feature toFeature(FeatureCreateRequest featureCreateRequest);
    FeatureCreateResponse toFeatureCreateResponse(Feature feature);
    FeatureResponse toFeatureResponse(Feature feature);
}
