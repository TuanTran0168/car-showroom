package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.payload.request.color.ColorCreateRequest;
import com.tuantran.CarShowroom.payload.request.color.ColorUpdateRequest;
import com.tuantran.CarShowroom.payload.response.color.ColorCreateResponse;
import com.tuantran.CarShowroom.payload.response.color.ColorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorResponse toColorResponse(Color color);
    ColorCreateResponse toColorCreateResponse(Color color);
    Color toColor(ColorCreateRequest colorCreateRequest);
    void updateColor(@MappingTarget Color color, ColorUpdateRequest colorUpdateRequest);
}
