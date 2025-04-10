package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.entity.Image;
import com.tuantran.CarShowroom.mapper.CarImageMapper;
import com.tuantran.CarShowroom.mapper.CarImageMapperCustom;
import com.tuantran.CarShowroom.mapper.CarMapper;
import com.tuantran.CarShowroom.mapper.ImageMapper;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageUpdateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageUpdateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageResponse;
import com.tuantran.CarShowroom.payload.response.cloudinary.CloudinaryUploadResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageResponse;
import com.tuantran.CarShowroom.repository.CarImageRepository;
import com.tuantran.CarShowroom.repository.CarRepository;
import com.tuantran.CarShowroom.repository.ColorRepository;
import com.tuantran.CarShowroom.repository.ImageRepository;
import com.tuantran.CarShowroom.service.CarImageService;
import com.tuantran.CarShowroom.service.cloudinary.CloudinaryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarImageServiceImpl implements CarImageService {

    @Autowired
    private CarImageRepository carImageRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CarImageMapper carImageMapper;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private CarImageMapperCustom carImageMapperCustom;

    @Override
    public CarImageCreateResponse createCarImage(CarImageCreateRequest carImageCreateRequest) {
        CarImage carImage = this.carImageMapper.toCarImage(carImageCreateRequest);

        Car car = this.carRepository.findById(carImageCreateRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Color color = this.colorRepository.findById(carImageCreateRequest.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        carImage.setCar(car);
        carImage.setColor(color);

        this.carImageRepository.save(carImage);

        List<Image> imageList = new ArrayList<>();

        for (ImageCreateRequest imageCreateRequest : carImageCreateRequest.getImageCreateRequests()) {
            MultipartFile file = imageCreateRequest.getFile();
            try {
                CloudinaryUploadResponse cloudinaryUploadResponse = this.cloudinaryService.uploadFile(file);
                Image image = Image.builder()
                        .carImage(carImage)
                        .url(cloudinaryUploadResponse.getUrl())
                        .publicId(cloudinaryUploadResponse.getPublicId())
                        .build();

                this.imageRepository.save(image);

                imageList.add(image);

            } catch (IOException e) {
                throw new RuntimeException("Error uploading file to Cloudinary", e);
            }
        }

        carImage.setImageList(imageList);

        this.carImageRepository.save(carImage);

        return this.carImageMapperCustom.toCarImageCreateResponse(carImage);
    }

    @Override
    public CarImageResponse updateCarImage(long id, CarImageUpdateRequest carImageUpdateRequest) {
        CarImage carImage = this.carImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car image not found"));
        if (carImageUpdateRequest.getCarId() != 0) {
            Car car = this.carRepository.findById(carImageUpdateRequest.getCarId())
                    .orElseThrow(() -> new RuntimeException("Car not found"));
            carImage.setCar(car);
        }
        if (carImageUpdateRequest.getColorId() != 0) {
            Color color = this.colorRepository.findById(carImageUpdateRequest.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));
            carImage.setColor(color);
        }
        // Delete images if any are present in RemoveImageIds (cloudinary)
        if (carImageUpdateRequest.getRemoveImageIds() != null && !carImageUpdateRequest.getRemoveImageIds().isEmpty()) {
            List<Image> imagesToRemove = imageRepository.findAllById(carImageUpdateRequest.getRemoveImageIds());
            for (Image image : imagesToRemove) {
                cloudinaryService.deleteFile(image.getPublicId());
                imageRepository.delete(image);
            }
            // Remove current images from Entity
            carImage.getImageList().removeIf(img -> carImageUpdateRequest.getRemoveImageIds().contains(img.getId()));
        }

        if (carImageUpdateRequest.getImageUpdateRequests() != null) {
            for (ImageUpdateRequest imageUpdateRequest : carImageUpdateRequest.getImageUpdateRequests()) {
                MultipartFile file = imageUpdateRequest.getFile();
                try {
                    CloudinaryUploadResponse uploadResponse = cloudinaryService.uploadFile(file);
                    Image image = Image.builder()
                            .carImage(carImage)
                            .url(uploadResponse.getUrl())
                            .publicId(uploadResponse.getPublicId())
                            .build();
                    imageRepository.save(image);

                    if (carImage.getImageList() == null) {
                        carImage.setImageList(new ArrayList<>());
                    }
                    carImage.getImageList().add(image);
                } catch (IOException e) {
                    throw new RuntimeException("Error uploading file to Cloudinary", e);
                }
            }
        }
        this.carImageRepository.save(carImage);

        return this.carImageMapperCustom.toCarImageResponse(carImage);
    }
}

