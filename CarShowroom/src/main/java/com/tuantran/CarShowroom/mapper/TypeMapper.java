package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.Type;
import com.tuantran.CarShowroom.payload.request.type.TypeCreateRequest;
import com.tuantran.CarShowroom.payload.request.type.TypeUpdateRequest;
import com.tuantran.CarShowroom.payload.response.type.TypeCreateResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeResponse toTypeResponse(Type type);
    Type toType(TypeCreateRequest typeCreateRequest);
    TypeCreateResponse toTypeCreateResponse(Type type);
    void updateType(@MappingTarget Type type, TypeUpdateRequest typeUpdateRequest);
}
