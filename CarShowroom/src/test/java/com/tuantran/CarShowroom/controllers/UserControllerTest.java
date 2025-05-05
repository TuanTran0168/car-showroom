package com.tuantran.CarShowroom.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;
import com.tuantran.CarShowroom.payload.response.role.RoleResponse;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private RoleResponse roleResponse;
    private UserCreateRequest userCreateRequest;
    private UserCreateResponse userCreateResponse;

    @BeforeEach
    void initData() {
        log.info("Init Data");
        userCreateRequest = UserCreateRequest.builder()
                .name("Tuấn Trần Test!")
                .username("tuantest")
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
    }

    @Test
    void createUserTest_validRequest_success() throws Exception {
        log.info("createUserTest_validRequest_success");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(userCreateRequest);

        // Mock service
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userCreateResponse);

        // Perform request and get result
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("AAAAAA: " + responseContent);

        // Convert response JSON -> Object
        // DTO must have 2 annotation: @NoArgsConstructor, @AllArgsConstructor for objectMapper
        UserCreateResponse actualResponse = objectMapper.readValue(responseContent, UserCreateResponse.class);

        // Assert
//        assertEquals(userCreateResponse.getId(), actualResponse.getId());
        assertEquals(userCreateRequest.getName(), actualResponse.getName());
        assertEquals(userCreateRequest.getUsername(), actualResponse.getUsername());
//        assertEquals(userCreateResponse.getRole().getId(), actualResponse.getRole().getId());
//        assertEquals(userCreateResponse.getRole().getName(), actualResponse.getRole().getName());
    }

    @Test
    void createUserTest_invalidUsername_fail() throws Exception {
        log.info("createUserTest_invalidUsername_fail");

        ObjectMapper objectMapper = new ObjectMapper();
        userCreateRequest.setUsername("ab");

        String content = objectMapper.writeValueAsString(userCreateRequest);

        // Mock service (No need this process)
//        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userCreateResponse);

        // Perform request and get result
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors.username").value("size must be between 3 and 2147483647"))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void createUserTest_invalidPassword_fail() throws Exception {
        log.info("createUserTest_invalidPassword_fail");

        ObjectMapper objectMapper = new ObjectMapper();
        userCreateRequest.setPassword("12345");

        String content = objectMapper.writeValueAsString(userCreateRequest);

        // Mock service (No need this process)
//        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userCreateResponse);

        // Perform request and get result
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors.password").value("size must be between 6 and 2147483647"))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
