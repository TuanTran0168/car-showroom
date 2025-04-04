package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Role;
import com.tuantran.CarShowroom.entity.Type;
import com.tuantran.CarShowroom.mapper.TypeMapper;
import com.tuantran.CarShowroom.payload.request.type.TypeCreateRequest;
import com.tuantran.CarShowroom.payload.request.type.TypeUpdateRequest;
import com.tuantran.CarShowroom.payload.response.type.TypeCreateResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeResponse;
import com.tuantran.CarShowroom.repository.RoleRepository;
import com.tuantran.CarShowroom.repository.TypeRepository;
import com.tuantran.CarShowroom.service.TypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<TypeResponse> findAll() {
        return this.typeRepository.findAll().stream().map(typeMapper::toTypeResponse).toList();
    }

    @Override
    public Page<TypeResponse> findAll(Pageable pageable) {
        return this.typeRepository.findAll(pageable).map(typeMapper::toTypeResponse);
    }

    @Override
    public TypeCreateResponse createType(TypeCreateRequest typeCreateRequest) {
        Type type = typeMapper.toType(typeCreateRequest);
        Type savedType = this.typeRepository.save(type);
        return typeMapper.toTypeCreateResponse(savedType);
    }

    @Override
    public TypeResponse updateType(int id, TypeUpdateRequest typeUpdateRequest) {
        Type typeToUpdate = this.typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        this.typeMapper.updateType(typeToUpdate, typeUpdateRequest);

        Type updatedType = this.typeRepository.save(typeToUpdate);
        return this.typeMapper.toTypeResponse(updatedType);
    }

    @Override
    public TypeResponse findById(int id) {
        Type type = this.typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        return this.typeMapper.toTypeResponse(type);
    }
}
