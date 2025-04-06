package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateCreateRequest;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateUpdateRequest;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateCreateResponse;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarTemplateMapper {
    CarTemplate toCarTemplate(CarTemplateCreateRequest carTemplateCreateRequest);
    CarTemplateCreateResponse toCarTemplateCreateResponse(CarTemplate carTemplate);
    CarTemplateResponse toCarTemplateResponse(CarTemplate carTemplate);
    void updateCarTemplate(@MappingTarget CarTemplate carTemplate, CarTemplateUpdateRequest carTemplateUpdateRequest);
}
