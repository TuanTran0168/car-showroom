package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.request.car.CarCreateRequest;
import com.tuantran.CarShowroom.payload.response.car.CarCreateResponse;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 🔹 Create a new car
     */
    @PostMapping
    public ResponseEntity<CarCreateResponse> createCar(@Valid @RequestBody CarCreateRequest carCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(carCreateRequest));
    }

    /**
     * 🔹 Get all cars
     */
    @GetMapping
    public ResponseEntity<List<CarResponse>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }
}
