package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.payload.request.brand.BrandCreateRequest;
import com.tuantran.CarShowroom.payload.request.brand.BrandUpdateRequest;
import com.tuantran.CarShowroom.payload.response.brand.BrandCreateResponse;
import com.tuantran.CarShowroom.payload.response.brand.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toBrandResponse(Brand brand);
    Brand toBrand(BrandCreateRequest brandCreateRequest);
    BrandCreateResponse toBrandCreateResponse(Brand brand);
    void updateBrand(@MappingTarget Brand brand, BrandUpdateRequest brandUpdateRequest);
}
