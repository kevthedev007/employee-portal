package com.interswitch.employeeportal.repository;

import com.interswitch.employeeportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByManagerId(Long id);
    Optional<Department> findByName(String name);
}
