package com.tuantran.CarShowroom.controllers;


import com.tuantran.CarShowroom.entity.Brand;
import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.payload.response.brand.BrandResponse;
import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import com.tuantran.CarShowroom.service.BrandService;
import com.tuantran.CarShowroom.service.SegmentService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.GenericSpecificationUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private SegmentService segmentService;

    @GetMapping("/test")
    public ResponseEntity<List<Brand>> findTest() {
        return ResponseEntity.ok(brandService.findAllTest());
    }

    /**
     * 🔹 Get all brands
     */
    @GetMapping
    public ResponseEntity<List<BrandResponse>> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    /**
     * 🔹 Get all brands
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<BrandResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<Brand>> listSpecification = new ArrayList<>();

        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }

        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        Specification<Brand> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        return ResponseEntity.ok(brandService.findAll(specification, pageable));
    }

    /**
     * 🔹 Get brand by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.brandService.findById(id));
    }

    /**
     * 🔹 Get segment by brandId
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/{id}/segments/page")
    public ResponseEntity<Page<SegmentResponse>> findSegmentByBrandId(
            @PathVariable int id,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all
    ) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(this.segmentService.findByBrand(id, pageable));
    }
}
