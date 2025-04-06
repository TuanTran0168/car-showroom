package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateCreateRequest;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateCreateResponse;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarTemplateMapper {
    CarTemplate toCarTemplate(CarTemplateCreateRequest carTemplateCreateRequest);
    CarTemplateCreateResponse toCarCreateResponse(CarTemplate carTemplate);
    CarTemplateResponse toCarTemplateResponse(CarTemplate carTemplate);
}
