package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.mapper.ColorMapper;
import com.tuantran.CarShowroom.payload.request.color.ColorCreateRequest;
import com.tuantran.CarShowroom.payload.request.color.ColorUpdateRequest;
import com.tuantran.CarShowroom.payload.response.color.ColorCreateResponse;
import com.tuantran.CarShowroom.payload.response.color.ColorResponse;
import com.tuantran.CarShowroom.repository.ColorRepository;
import com.tuantran.CarShowroom.service.ColorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<ColorResponse> findAll() {
        return this.colorRepository.findAll().stream().map(colorMapper::toColorResponse).toList();
    }

    @Override
    public Page<ColorResponse> findAll(Pageable pageable) {
        return this.colorRepository.findAll(pageable).map(colorMapper::toColorResponse);
    }

    @Override
    public Page<ColorResponse> findAll(Specification<Color> specification, Pageable pageable) {
        return this.colorRepository.findAll(specification, pageable).map(colorMapper::toColorResponse);
    }

    @Override
    public ColorResponse findById(long id) {
        return this.colorMapper.toColorResponse(this.colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found")));
    }

    @Override
    public ColorCreateResponse createColor(ColorCreateRequest colorCreateRequest) {
        Color color = this.colorMapper.toColor(colorCreateRequest);

        return this.colorMapper.toColorCreateResponse(this.colorRepository.save(color));
    }

    @Override
    public ColorResponse updateColor(long id, ColorUpdateRequest color) {
        Color colorUpdate = this.colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Color not found"));
        this.colorMapper.updateColor(colorUpdate, color);
        return this.colorMapper.toColorResponse(this.colorRepository.save(colorUpdate));
    }

}
