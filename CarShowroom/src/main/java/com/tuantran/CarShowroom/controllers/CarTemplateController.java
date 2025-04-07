package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.entity.CarTemplate;
import com.tuantran.CarShowroom.mapper.CarTemplateMapper;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateCreateRequest;
import com.tuantran.CarShowroom.payload.request.cartemplate.CarTemplateUpdateRequest;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateCreateResponse;
import com.tuantran.CarShowroom.payload.response.cartemplate.CarTemplateResponse;
import com.tuantran.CarShowroom.service.CarTemplateService;
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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/car-templates")
public class CarTemplateController {

    @Autowired
    private CarTemplateService carTemplateService;

    @Autowired
    private CarTemplateMapper carTemplateMapper;

    /**
     * 🔹 Get all car templates
     */
    @GetMapping
    public ResponseEntity<List<CarTemplateResponse>> findAll() {
        return ResponseEntity.ok(this.carTemplateService.findAll());
    }

    /**
     * 🔹 Get all car templates
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<CarTemplateResponse>> findAllPageParams(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<CarTemplate>> listSpecification = new ArrayList<>();
        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }
        Specification<CarTemplate> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(carTemplateService.findAll(specification, pageable));
    }

    /**
     * 🔹 Get car template by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarTemplateResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.carTemplateService.findById(id));
    }

    /**
     * 🔹 Create a new car template
     */
    @PostMapping
    public ResponseEntity<CarTemplateCreateResponse> createCarTemplate(
            @Valid @RequestBody CarTemplateCreateRequest carTemplateCreateRequest) {
        CarTemplateCreateResponse carTemplateCreateResponse = this.carTemplateService.createCarTemplate(carTemplateCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(carTemplateCreateResponse);
    }

    /**
     * 🔹 Update a car template
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CarTemplateResponse> updateCarTemplate(@PathVariable long id,
                                                                 @Valid @RequestBody CarTemplateUpdateRequest carTemplateUpdateRequest) {
        return ResponseEntity.ok(this.carTemplateService.updateCarTemplate(id, carTemplateUpdateRequest));
    }

}
