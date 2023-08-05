package com.interswitch.employeeportal.service;

import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.dto.SalaryRateDto;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.entity.SalaryRate;

import java.util.List;

public interface SalaryRateService {
    SalaryRateDto createSalaryRate(SalaryRateDto salaryRateDto);
    List<SalaryRateDto> getAllSalaryRates();
    SalaryRateDto getSalaryRateById(Long id);
    SalaryRateDto updateSalaryRate(SalaryRateDto salaryRateDto, Long id);
    String DeleteSalaryRate(Long id);
    List<EmployeeDto> getEmployeesBySalaryRate(Long id);
}
