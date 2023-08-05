package com.interswitch.employeeportal.service;

import com.interswitch.employeeportal.dto.EmployeeCategoryDto;
import com.interswitch.employeeportal.entity.EmployeeCategory;

import java.util.List;

public interface EmployeeCategoryService {
    EmployeeCategoryDto createEmployeeCategory(EmployeeCategoryDto employeeCategoryDto);
    List<EmployeeCategoryDto> findAllEmployeeCategory();
    EmployeeCategoryDto findEmployeeCategoryById(Long id);
    EmployeeCategoryDto updateEmployeeCategory(EmployeeCategoryDto employeeCategoryDto, Long id);
    String deleteEmployeeCategory(Long id);
}
