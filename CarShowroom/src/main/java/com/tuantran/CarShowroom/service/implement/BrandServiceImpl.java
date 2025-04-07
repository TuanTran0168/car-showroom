package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.mapper.BrandMapper;
import com.tuantran.CarShowroom.payload.response.brand.BrandResponse;
import com.tuantran.CarShowroom.repository.BrandRepository;
import com.tuantran.CarShowroom.service.BrandService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandResponse> findAll() {
        return this.brandRepository.findAll().stream().map(brandMapper::toBrandResponse).toList();
    }

    @Override
    public List<Brand> findAllTest() {
        return this.brandRepository.findAll();
    }

    @Override
    public Page<BrandResponse> findAll(Specification<Brand> specification, Pageable pageable) {
        return this.brandRepository.findAll(specification, pageable).map(brandMapper::toBrandResponse);
    }

    @Override
    public BrandResponse findById(long id) {
        return this.brandRepository.findById(id).map(brandMapper::toBrandResponse)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }
}
