package com.interswitch.employeeportal.repository;

import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByEmployeeCategoryId(Long employeeCategoryId);
}
