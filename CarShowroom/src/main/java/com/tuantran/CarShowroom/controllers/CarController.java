package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.mapper.CarMapper;
import com.tuantran.CarShowroom.payload.response.car.CarResponse;
import com.tuantran.CarShowroom.payload.response.user.UserResponse;
import com.tuantran.CarShowroom.service.CarService;
import com.tuantran.CarShowroom.utils.FilterParamUtils;
import com.tuantran.CarShowroom.utils.GenericSpecificationUtils;
import com.tuantran.CarShowroom.utils.PageSizeUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    /**
     * 🔹 Get all cars
     */
    @GetMapping
    public ResponseEntity<List<CarResponse>> findAll() {
        return ResponseEntity.ok(this.carService.findAll());
    }

    /**
     * 🔹 Get all users
     * 🔹 Pagination
     * 🔹 Params (page, size, sort, direction, all) for swagger
     */
    @GetMapping("/page")
    public ResponseEntity<Page<CarResponse>> findAllPageParams(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "1" ) int page,
            @Parameter(description = "Size per page") @RequestParam(defaultValue = "5") int size,
            @Parameter(description = "Sort by") @RequestParam(required = false) String sort,
            @Parameter(description = "Direction") @RequestParam(required = false) String direction,
            @Parameter(description = "All data in one page") @RequestParam(defaultValue = "false" ) Boolean all,
            @Parameter(description = "Additional filter parameters", schema = @Schema(implementation = FilterParamUtils.class))
            @RequestParam Map<String, String> params
    ) throws MissingServletRequestParameterException {
        List<Specification<Car>> listSpecification = new ArrayList<>();
        if (params.containsKey("name")) {
            String name = params.get("name");
            listSpecification.add(GenericSpecificationUtils.fieldContains("name", name));
        }
        if (params.containsKey("active")) {
            boolean active = Boolean.parseBoolean(params.get("active"));
            listSpecification.add(GenericSpecificationUtils.fieldEquals("active", active));
        }
        Specification<Car> specification = GenericSpecificationUtils.combineSpecification(listSpecification);
        Pageable pageable = PageSizeUtils.getPageable(page, size, sort, direction, all);
        return ResponseEntity.ok(carService.findAll(specification, pageable));
    }

    /**
     * 🔹 Get car by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.carService.findById(id));
    }

    /**
     * 🔹 Create a new car
     */

}
