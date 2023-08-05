package com.interswitch.employeeportal.service;

import com.interswitch.employeeportal.dto.DepartmentDto;
import com.interswitch.employeeportal.dto.EmployeeDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);
    String deleteDepartment(Long id);
    List<EmployeeDto> findEmployeesByDepartment(Long id);
}
