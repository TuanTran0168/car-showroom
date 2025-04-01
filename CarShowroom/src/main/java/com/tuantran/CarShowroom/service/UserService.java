package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.User;

public interface UserService {
    User loadUserByUsername(String username);
}
