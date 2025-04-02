package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import com.tuantran.CarShowroom.service.UserService;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 🔹 Create a new user
     */
    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserCreateResponse userCreateResponse = userService.createUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateResponse);
    }

    /**
     * 🔹 Create a new user
     */
    @PostMapping("/current-user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<UserResponse> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

    /**
     * 🔹 Get all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    /**
     * 🔹 Get all users
     * 🔹 Pagination
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>> findAllPage(@RequestParam Map<String, String> params) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(params);
        return ResponseEntity.ok(this.userService.findAll(pageable));
    }

}

//@Autowired
//private UserDetailsServiceImpl userDetailsService;
//
//@Autowired
//private JwtService jwtService;
//
//@Autowired
//private RoleServiceImpl roleServiceImplement;
//
//@Autowired
//private AuthenticationManager authenticationManager;
//
//@GetMapping("/welcome")
//public String welcome() {
//    return "Welcome this endpoint is not secure";
//}
//
//@PostMapping("/addNewUser")
//public String addNewUser(@RequestBody UserCreateRequest userCreateRequest) {
//    User user = new User();
//    user.setName(userCreateRequest.getName());
//    user.setUsername(userCreateRequest.getUsername());
//    user.setPassword(userCreateRequest.getPassword());
//    user.setRole(roleServiceImplement.findById(Integer.parseInt(userCreateRequest.getRole_id())));
//    return userDetailsService.addUser(user);
//}
//
//@GetMapping("/user/userProfile")
//@PreAuthorize("hasAuthority('ROLE_USER')")
//public String userProfile() {
//    return "Welcome to User Profile";
//}
//
//@GetMapping("/admin/adminProfile")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//public String adminProfile() {
//    return "Welcome to Admin Profile";
//}