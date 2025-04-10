package com.tuantran.CarShowroom.payload.request.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUpdateRequest {
    MultipartFile file;
}
