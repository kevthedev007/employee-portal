package com.interswitch.employeeportal.service;

import com.interswitch.employeeportal.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
    String deleteEmployee(Long id);
    List<EmployeeDto> getEmployeesByManager(Long employeeId);
}
