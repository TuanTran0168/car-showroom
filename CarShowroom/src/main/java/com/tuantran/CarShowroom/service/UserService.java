package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserCreateResponse createUser(UserCreateRequest userCreateRequest);

    List<UserResponse> getAllUsers();

    Page<UserResponse> findAll(Pageable pageable);
}
