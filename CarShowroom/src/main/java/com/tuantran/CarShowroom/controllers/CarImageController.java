package com.tuantran.CarShowroom.controllers;


import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.mapper.CarImageMapper;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageUpdateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageUpdateRequest;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import com.tuantran.CarShowroom.service.CarImageService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.GenericSpecificationUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/car-images")
public class CarImageController {

    @Autowired
    private CarImageService carImageService;

    @Autowired
    private CarImageMapper carImageMapper;

    /**
     * 🔹 Create a new car image
     */
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CarImageCreateResponse> createCarImage(
            @Valid CarImageCreateRequest carImageCreateRequest,
            @RequestPart("files") List<MultipartFile> files) {

        for (MultipartFile file : files) {
            ImageCreateRequest imageCreateRequest = new ImageCreateRequest();
            imageCreateRequest.setFile(file);
            carImageCreateRequest.getImageCreateRequests().add(imageCreateRequest);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(carImageService.createCarImage(carImageCreateRequest));
    }

    /**
     * 🔹 Update a car image
     * 🔹 You can also delete existing images using the `removeImageIds` field.
     */
    @Operation(
            description = "You can also delete existing images using the `removeImageIds` field."
    )
    @PatchMapping(path = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CarImageResponse> updateCarImage(@PathVariable long id,
                                                           @Valid CarImageUpdateRequest carImageUpdateRequest,
                                                           @RequestPart("files") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            ImageUpdateRequest imageUpdateRequest = new ImageUpdateRequest();
            imageUpdateRequest.setFile(file);
            carImageUpdateRequest.getImageUpdateRequests().add(imageUpdateRequest);
        }
        return ResponseEntity.ok(carImageService.updateCarImage(id, carImageUpdateRequest));
    }

    /**
     * 🔹 Get all car images
     */
    @GetMapping
    public ResponseEntity<List<CarImageResponse>> findAll() {
        return ResponseEntity.ok(this.carImageService.findAll());
    }

    /**
     * 🔹 Get all car images
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<CarImageResponse>> findAllPage(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<CarImage>> listSpecification = new ArrayList<>();

        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }

        Specification<CarImage> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(carImageService.findAll(specification, pageable));
    }
}
