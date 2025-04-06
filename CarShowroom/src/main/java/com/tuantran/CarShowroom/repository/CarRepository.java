package com.tuantran.CarShowroom.repository;


import com.tuantran.CarShowroom.entity.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAll(Specification<Car> specification, Pageable pageable);
}
