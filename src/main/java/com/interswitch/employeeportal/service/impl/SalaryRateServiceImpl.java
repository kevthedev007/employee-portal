package com.interswitch.employeeportal.service.impl;

import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.dto.SalaryRateDto;
import com.interswitch.employeeportal.entity.Employee;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.entity.SalaryRate;
import com.interswitch.employeeportal.exception.PayrollException;
import com.interswitch.employeeportal.exception.ResourceNotFoundException;
import com.interswitch.employeeportal.repository.EmployeeCategoryRepository;
import com.interswitch.employeeportal.repository.EmployeeRepository;
import com.interswitch.employeeportal.repository.SalaryRateRepository;
import com.interswitch.employeeportal.service.SalaryRateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryRateServiceImpl implements SalaryRateService {

    private final SalaryRateRepository salaryRateRepository;
    private final EmployeeCategoryRepository employeeCategoryRepository;
    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;

    @Autowired
    public SalaryRateServiceImpl(
            SalaryRateRepository salaryRateRepository,
            EmployeeCategoryRepository employeeCategoryRepository,
            EmployeeRepository employeeRepository,
            ModelMapper mapper) {
        this.salaryRateRepository = salaryRateRepository;
        this.employeeCategoryRepository = employeeCategoryRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public SalaryRateDto createSalaryRate(SalaryRateDto salaryRateDto) {
        EmployeeCategory employeeCategory= employeeCategoryRepository.findById(salaryRateDto.getEmployeeCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeCategory", "id", salaryRateDto.getEmployeeCategoryId()));
        SalaryRate salaryRate = mapToEntity(salaryRateDto);
        salaryRate.setEmployeeCategory(employeeCategory);
        SalaryRate salaryRateFromDb = salaryRateRepository.save(salaryRate);
        return mapToDto(salaryRateFromDb);
    }

    @Override
    public List<SalaryRateDto> getAllSalaryRates() {
        List<SalaryRate> salaryRates =  salaryRateRepository.findAll();
        return salaryRates.stream().map(salaryRate -> mapToDto(salaryRate))
                .collect(Collectors.toList());
    }


    @Override
    public SalaryRateDto getSalaryRateById(Long id) {
        SalaryRate salaryRate = salaryRateRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SalaryRate", "id", id));

        return mapToDto(salaryRate);
    }

    @Override
    public SalaryRateDto updateSalaryRate(SalaryRateDto salaryRateDto, Long id) {
        SalaryRate salaryRate = salaryRateRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SalaryRate", "id", id));

        salaryRate.setAmount(salaryRateDto.getAmount());
        salaryRate.setLevel(salaryRateDto.getLevel());

        SalaryRate updatedSalaryRate = salaryRateRepository.save(salaryRate);

        return mapToDto(updatedSalaryRate);
    }


    @Override
    public String DeleteSalaryRate(Long id) {
        SalaryRate salaryRate = salaryRateRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SalaryRate", "id", id));

        salaryRateRepository.delete(salaryRate);
        return "Salary rate deleted successfully";
    }

    @Override
    public List<EmployeeDto> getEmployeesBySalaryRate(Long id) {
        SalaryRate salaryRate = salaryRateRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SalaryRate", "id", id));

        List<Employee> employees = employeeRepository.findByEmployeeCategoryId(salaryRate.getEmployeeCategory().getId());

        return employees.stream().map(employee -> mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }


    private SalaryRateDto mapToDto(SalaryRate salaryRate) {
        SalaryRateDto salaryRateDto = mapper.map(salaryRate, SalaryRateDto.class);
        return salaryRateDto;
    }

    private SalaryRate mapToEntity(SalaryRateDto salaryRateDto) {
        SalaryRate salaryRate = mapper.map(salaryRateDto, SalaryRate.class);
        return salaryRate;
    }

    private EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
