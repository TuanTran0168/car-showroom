package com.tuantran.CarShowroom.service.implement;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuantran.CarShowroom.entity.Role;
import com.tuantran.CarShowroom.repository.RoleRepository;
import com.tuantran.CarShowroom.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

}
