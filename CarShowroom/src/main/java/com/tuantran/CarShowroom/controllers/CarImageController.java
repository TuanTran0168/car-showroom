package com.tuantran.CarShowroom.controllers;


import com.tuantran.CarShowroom.mapper.CarImageMapper;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.service.CarImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
}
