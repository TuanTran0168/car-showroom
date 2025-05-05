package com.tuantran.CarShowroom.service;

import com.tuantran.CarShowroom.entity.Role;
import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.mapper.UserMapper;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.role.RoleResponse;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockitoBean
    UserRepository userRepository;

    private RoleResponse roleResponse;
    private UserCreateRequest userCreateRequest;
    private UserCreateResponse userCreateResponse;
    private User user;

    @BeforeEach
    void initData() {
        log.info("Init Data");
        userCreateRequest = UserCreateRequest.builder()
                .name("Tuấn Trần Test Service!")
                .username("tuantestservice")
                .password("123456")
                .build();

        roleResponse = RoleResponse.builder()
                .id(9999)
                .name("ROLE_USER")
                .build();
        userCreateResponse = UserCreateResponse.builder()
                .id(9999)
                .name("Tuấn Trần Test!")
                .username("tuantest")
                .role(roleResponse)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();

        user = User.builder()
                .name("Tuấn Trần Test Service!")
                .username("tuantestservice")
                .role(new Role().builder().name("ROLE_USER").build())
                .build();
    }

    @Test
    void createUserTest_validRequest_success() {

        // GIVEN
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        // WHEN
        UserCreateResponse userCreateResponse = userService.createUser(userCreateRequest);

        // THEN
        Assertions.assertThat(userCreateResponse.getName()).isEqualTo("Tuấn Trần Test Service!");
        Assertions.assertThat(userCreateResponse.getUsername()).isEqualTo("tuantestservicee");
        Assertions.assertThat(userCreateResponse.getRole().getName()).isEqualTo("ROLE_USER");
    }

    @Test
    void createUserTest_userExisted_fail() {

        // GIVEN
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(true);

        // WHEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(userCreateRequest));

        // THEN
        Assertions.assertThat(exception.getMessage()).isEqualTo("Username already exists");
    }
}
