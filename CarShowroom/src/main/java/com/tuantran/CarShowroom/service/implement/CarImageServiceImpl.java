package com.tuantran.CarShowroom.service.implement;

import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.CarImage;
import com.tuantran.CarShowroom.entity.Color;
import com.tuantran.CarShowroom.entity.Image;
import com.tuantran.CarShowroom.mapper.CarImageMapper;
import com.tuantran.CarShowroom.mapper.CarMapper;
import com.tuantran.CarShowroom.mapper.ImageMapper;
import com.tuantran.CarShowroom.payload.request.carimage.CarImageCreateRequest;
import com.tuantran.CarShowroom.payload.request.image.ImageCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.carimage.CarImageCreateResponse;
import com.tuantran.CarShowroom.payload.response.cloudinary.CloudinaryUploadResponse;
import com.tuantran.CarShowroom.payload.response.image.ImageCreateResponse;
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
                throw new RuntimeException(e);
            }
        }

        carImage.setImageList(imageList);

        this.carImageRepository.save(carImage);

        CarImageCreateResponse carImageCreateResponse = this.carImageMapper.toCarImageCreateResponse(carImage);
        carImage.getImageList().forEach(image -> {
            ImageCreateResponse imageCreateResponse = this.imageMapper.toImageCreateResponse(image);
            carImageCreateResponse.getImages().add(imageCreateResponse);
        });

        return carImageCreateResponse;
    }
}

