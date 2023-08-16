package com.interswitch.employeeportal.repository;

import com.interswitch.employeeportal.entity.EmployeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, Long> {
    Optional<EmployeeCategory> findByName(String name);
}
