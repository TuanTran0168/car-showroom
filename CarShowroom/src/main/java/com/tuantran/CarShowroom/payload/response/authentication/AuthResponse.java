package com.tuantran.CarShowroom.payload.response.authentication;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    String token;
    Boolean authenticated;
}
