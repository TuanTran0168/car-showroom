package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.entity.Feature;
import com.tuantran.CarShowroom.mapper.FeatureMapper;
import com.tuantran.CarShowroom.payload.response.feature.FeatureResponse;
import com.tuantran.CarShowroom.payload.response.featurevalue.FeatureValueResponse;
import com.tuantran.CarShowroom.service.FeatureService;
import com.tuantran.CarShowroom.service.FeatureValueService;
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
@RequestMapping("/api/v1/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private FeatureMapper featureMapper;

    @Autowired
    private FeatureValueService featureValueService;

    /**
     * 🔹 Get all features
     */
    @GetMapping
    public ResponseEntity<List<FeatureResponse>> findAll() {
        return ResponseEntity.ok(this.featureService.findAll());
    }

    /**
     * 🔹 Get all features
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<FeatureResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<Feature>> listSpecification = new ArrayList<>();

        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }

        Specification<Feature> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(this.featureService.findAll(specification, pageable));
    }

    /**
     * 🔹 Get feature by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeatureResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.featureService.findById(id));
    }

    /**
     * 🔹 Get feature value by featureId
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/{id}/feature-values/page")
    public ResponseEntity<Page<FeatureValueResponse>> findValueByFeatureId(
            @PathVariable int id,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all
    ) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(this.featureValueService.findByFeature(id, pageable));
    }
}
