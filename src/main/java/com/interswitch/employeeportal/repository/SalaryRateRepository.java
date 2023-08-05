package com.interswitch.employeeportal.repository;

import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.entity.SalaryRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRateRepository extends JpaRepository<SalaryRate, Long> {
    List<SalaryRate> findByEmployeeCategoryId(Long id);
}
