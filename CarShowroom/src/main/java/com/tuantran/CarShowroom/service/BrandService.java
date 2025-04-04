package com.tuantran.CarShowroom.service;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.payload.response.brand.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BrandService {
    List<BrandResponse> findAll();
    List<Brand> findAllTest();
    Page<BrandResponse> findAll(Specification<Brand> specification, Pageable pageable);
    BrandResponse findById(int id);
}
