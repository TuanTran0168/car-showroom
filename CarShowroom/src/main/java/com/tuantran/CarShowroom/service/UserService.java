package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserCreateResponse createUser(UserCreateRequest userCreateRequest);

    List<UserResponse> getAllUsers();

    Page<UserResponse> findAll(Specification<User> specification, Pageable pageable);

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse findByUsername(String username);

    UserResponse findById(long id);
}
