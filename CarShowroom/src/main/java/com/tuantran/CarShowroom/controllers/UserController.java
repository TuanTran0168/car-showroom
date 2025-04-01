package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.request.UserCreateRequest;
import com.tuantran.CarShowroom.service.implement.RoleServiceImpl;
import com.tuantran.CarShowroom.service.security.JwtService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleServiceImpl roleServiceImplement;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setName(userCreateRequest.getName());
        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(userCreateRequest.getPassword());
        user.setRole(roleServiceImplement.findById(Integer.parseInt(userCreateRequest.getRole_id())));
        return userDetailsService.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

}
