package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.response.cloudinary.CloudinaryUploadResponse;
import com.tuantran.CarShowroom.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cloudinary")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping(path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CloudinaryUploadResponse uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return this.cloudinaryService.uploadFile(file);
    }

    @DeleteMapping("/delete/{publicId}")
    public ResponseEntity<?> deleteFile(@PathVariable String publicId) {
        String result = cloudinaryService.deleteFile(publicId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/upload-many",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CloudinaryUploadResponse>> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {
        List<CloudinaryUploadResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                CloudinaryUploadResponse response = cloudinaryService.uploadFile(file);
                responses.add(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(responses);
    }

}
