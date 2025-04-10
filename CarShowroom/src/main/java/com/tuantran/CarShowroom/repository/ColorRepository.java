package com.tuantran.CarShowroom.repository;

import com.tuantran.CarShowroom.entity.Color;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByName(String name);
    Page<Color> findAll(Pageable pageable);
    Page<Color> findAll(Specification<Color> specification, Pageable pageable);
}
