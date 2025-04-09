package com.tuantran.CarShowroom.controllers;


import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.mapper.ColorMapper;
import com.tuantran.CarShowroom.payload.request.color.ColorCreateRequest;
import com.tuantran.CarShowroom.payload.request.color.ColorUpdateRequest;
import com.tuantran.CarShowroom.payload.response.color.ColorCreateResponse;
import com.tuantran.CarShowroom.payload.response.color.ColorResponse;
import com.tuantran.CarShowroom.service.ColorService;
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
@RequestMapping("/api/v1/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @Autowired
    ColorMapper colorMapper;

    /**
     * 🔹 Create a new color
     */
    @PostMapping
    public ResponseEntity<ColorCreateResponse> createColor(@Valid @RequestBody ColorCreateRequest colorCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.colorService.createColor(colorCreateRequest));
    }

    /**
     * 🔹 Update a color
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ColorResponse> updateColor(@PathVariable long id, @Valid @RequestBody ColorUpdateRequest colorUpdateRequest) {
        return ResponseEntity.ok(this.colorService.updateColor(id, colorUpdateRequest));
    }

    /**
     * 🔹 Get all colors
     */
    @GetMapping
    public ResponseEntity<List<ColorResponse>> findAll() {
        return ResponseEntity.ok(this.colorService.findAll());
    }

    /**
     * 🔹 Get all colors
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<ColorResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<Color>> listSpecification = new ArrayList<>();

        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }

        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        Specification<Color> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        return ResponseEntity.ok(this.colorService.findAll(specification, pageable));
    }
}
