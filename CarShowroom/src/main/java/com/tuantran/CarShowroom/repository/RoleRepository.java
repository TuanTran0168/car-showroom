package com.tuantran.CarShowroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuantran.CarShowroom.entity.Role;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(int id);
}
