package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.request.type.TypeCreateRequest;
import com.tuantran.CarShowroom.payload.response.type.TypeCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserCreateResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import com.tuantran.CarShowroom.service.UserService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.GenericSpecificationUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import com.tuantran.CarShowroom.payload.request.user.UserCreateRequest;

import java.util.ArrayList;
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
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
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
    @GetMapping("/page-old")
    public ResponseEntity<Page<UserResponse>> findAllPage(@RequestParam Map<String, String> params) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(params);
        return ResponseEntity.ok(this.userService.findAll(pageable));
    }

    /**
     * 🔹 Get all users
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>> findAllPageParams(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<User>> listSpecification = new ArrayList<>();

        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }

        Specification<User> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(userService.findAll(specification, pageable));
    }

    /**
     * 🔹 Get user by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }
}
