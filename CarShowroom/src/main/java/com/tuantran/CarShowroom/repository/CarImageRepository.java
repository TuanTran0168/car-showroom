package com.tuantran.CarShowroom.repository;

import com.tuantran.CarShowroom.entity.Car;
import com.tuantran.CarShowroom.entity.CarImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    Page<CarImage> findAll(Specification<CarImage> specification, Pageable pageable);
    Page<CarImage> findByCar(Car car, Pageable pageable);
}
