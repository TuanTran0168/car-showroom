package com.tuantran.CarShowroom.payload.response.image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponse {
    private String id;
    private String url;
    private String publicId;
}
