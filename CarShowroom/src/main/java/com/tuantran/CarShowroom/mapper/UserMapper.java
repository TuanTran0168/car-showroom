package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);

    User toUser(UserCreateResponse userCreateResponse);

    UserCreateResponse toUserCreateResponse(User user);

    UserResponse toUserResponse(User user);

    //    mapper for Collections (CANNOT USE THIS MAPPER)
    //    @IterableMapping(elementTargetType = UserResponse.class)
    //    Page<UserResponse> toUserResponse(Page<User> users);
}
