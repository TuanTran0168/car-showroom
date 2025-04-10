package com.tuantran.CarShowroom.service.cloudinary;

import com.cloudinary.utils.ObjectUtils;
import com.tuantran.CarShowroom.configurations.cloudinary.CloudinaryConfig;
import com.tuantran.CarShowroom.payload.response.cloudinary.CloudinaryUploadResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class CloudinaryService {

    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    public CloudinaryUploadResponse uploadFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        Map<?, ?> result = this.cloudinaryConfig
                .cloudinary()
                .uploader()
                .upload(tempFile, ObjectUtils.asMap("resource_type", "auto"));

        String url = result.get("secure_url").toString();
        String publicId = result.get("public_id").toString();

        return new CloudinaryUploadResponse(url, publicId);
    }

    public String deleteFile(String publicId) {
        try {
            Map result = this.cloudinaryConfig.cloudinary().uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi xóa file trên Cloudinary", e);
        }
    }
}
