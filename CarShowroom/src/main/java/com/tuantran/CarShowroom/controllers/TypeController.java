package com.tuantran.CarShowroom.controllers;


import com.tuantran.CarShowroom.mapper.TypeMapper;
import com.tuantran.CarShowroom.payload.request.type.TypeCreateRequest;
import com.tuantran.CarShowroom.payload.request.type.TypeUpdateRequest;
import com.tuantran.CarShowroom.payload.response.type.TypeCreateResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeResponse;
import com.tuantran.CarShowroom.service.TypeService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 🔹 Get all types
     */
    @GetMapping
    public ResponseEntity<List<TypeResponse>> findAll() {
        return ResponseEntity.ok(typeService.findAll());
    }

    /**
     * 🔹 Get all types
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */

    @GetMapping("/page")
    public ResponseEntity<Page<TypeResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(typeService.findAll(pageable));
    }

    /**
     * 🔹 Create a new type
     */
    @PostMapping
    public ResponseEntity<TypeCreateResponse> createType(@Valid @RequestBody TypeCreateRequest typeCreateRequest) {
        TypeCreateResponse typeResponse = this.typeService.createType(typeCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeResponse);
    }

    /**
     * 🔹 Update a type
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TypeResponse> updateType(@PathVariable int id, @Valid @RequestBody TypeUpdateRequest typeUpdateRequest) {
        TypeResponse typeResponse = this.typeService.updateType(id, typeUpdateRequest);
        return ResponseEntity.ok(typeResponse);
    }

    /**
     * 🔹 Get type by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.typeService.findById(id));
    }
}
