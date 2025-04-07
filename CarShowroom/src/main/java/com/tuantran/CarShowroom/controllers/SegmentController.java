package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import com.tuantran.CarShowroom.service.SegmentService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/segments")
public class SegmentController {

    @Autowired
    private SegmentService segmentService;

    /**
     * 🔹 Get all segments
     */
    @GetMapping
    public ResponseEntity<List<SegmentResponse>> findAll() {
        return ResponseEntity.ok(segmentService.findAll());
    }

    /**
     * 🔹 Get all segments
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<SegmentResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(segmentService.findAll(pageable));
    }

    /**
     * 🔹 Get segment by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<SegmentResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.segmentService.findById(id));
    }
}
