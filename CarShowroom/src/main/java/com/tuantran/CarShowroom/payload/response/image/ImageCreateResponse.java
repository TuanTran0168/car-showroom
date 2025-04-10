package com.tuantran.CarShowroom.payload.response.image;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ImageCreateResponse {
    private String id;
    private String url;
    private String publicId;
}
