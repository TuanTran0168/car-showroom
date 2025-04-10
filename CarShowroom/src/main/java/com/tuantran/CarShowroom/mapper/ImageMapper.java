package com.tuantran.CarShowroom.mapper;

import com.tuantran.CarShowroom.entity.Image;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.response.image.ImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageCreateResponse toImageCreateResponse(Image image);
    ImageResponse toImageResponse(Image image);
}
