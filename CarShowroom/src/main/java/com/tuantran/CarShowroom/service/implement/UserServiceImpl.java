package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Role;
import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.mapper.UserMapper;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import com.tuantran.CarShowroom.repository.RoleRepository;
import com.tuantran.CarShowroom.repository.UserRepository;
import com.tuantran.CarShowroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        if (this.userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User userEntity = this.userMapper.toUser(userCreateRequest);
        userEntity.setPassword(this.passwordEncoder.encode(userCreateRequest.getPassword()));

        Role role = this.roleRepository.findById(Integer.parseInt(userCreateRequest.getRole_id()))
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userEntity.setRole(role);

        User user = this.userRepository.save(userEntity);
        return this.userMapper.toUserCreateResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return this.userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
         return this.userRepository.findAll(pageable).map(userMapper::toUserResponse);

        // use mapper for Collections
        // mapper for Collections (CANNOT USE THIS MAPPER)
        // return this.userMapper.toUserResponse(this.userRepository.findAll(pageable));
    }

    @Override
    public UserResponse findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(userMapper::toUserResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
