package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import com.tuantran.CarShowroom.service.UserService;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
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
     * 🔹 Get current user by token
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
     * 🔹 Map<String, String>
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>> findAllPage(@RequestParam Map<String, String> params) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(params);
        return ResponseEntity.ok(this.userService.findAll(pageable));
    }

    /**
     * 🔹 Get all users
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction) for swagger
     */
    @GetMapping("/page-params")
    public ResponseEntity<Page<UserResponse>> findAllPageParams(
            @Parameter(description = "Page number") @RequestParam int page,
            @Parameter(description = "Size per page") @RequestParam int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction
    ) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction);
        return ResponseEntity.ok(userService.findAll(pageable));
    }
}
