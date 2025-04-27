package com.tuantran.CarShowroom.payload.response.user;

import com.tuantran.CarShowroom.payload.response.role.RoleResponse;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private long id;
    private String name;
    private String username;
    private RoleResponse role;
    private Date createdDate;
    private Date updatedDate;
}
