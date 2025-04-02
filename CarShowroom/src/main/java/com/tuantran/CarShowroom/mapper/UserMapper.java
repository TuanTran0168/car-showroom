package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);

    User toUser(UserCreateResponse userCreateResponse);

    UserCreateResponse toUserCreateResponse(User user);

    UserResponse toUserResponse(User user);
}
