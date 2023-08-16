package com.interswitch.employeeportal.service.impl;

import com.interswitch.employeeportal.dto.EmployeeCategoryDto;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.exception.PayrollException;
import com.interswitch.employeeportal.exception.ResourceNotFoundException;
import com.interswitch.employeeportal.repository.EmployeeCategoryRepository;
import com.interswitch.employeeportal.repository.EmployeeRepository;
import com.interswitch.employeeportal.service.EmployeeCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeCategoryServiceImpl implements EmployeeCategoryService {

    private final EmployeeCategoryRepository employeeCategoryRepository;
    private ModelMapper mapper;

    public EmployeeCategoryServiceImpl(EmployeeCategoryRepository employeeCategoryRepository, ModelMapper mapper) {
        this.employeeCategoryRepository = employeeCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeCategoryDto createEmployeeCategory(EmployeeCategoryDto employeeCategoryDto) {
        Optional<EmployeeCategory> categoryOptional = employeeCategoryRepository.findByName(employeeCategoryDto.getName());
        if (categoryOptional.isPresent()) {
            throw new PayrollException(HttpStatus.CONFLICT, "employee category already exists");
        }
        EmployeeCategory employeeCategory = mapToEntity(employeeCategoryDto);
        EmployeeCategory employeeCategoryFromDb = employeeCategoryRepository.save(employeeCategory);
        return mapToDto(employeeCategoryFromDb);
    }

    @Override
    public List<EmployeeCategoryDto> findAllEmployeeCategory() {
        List<EmployeeCategory> employeeCategories = employeeCategoryRepository.findAll();

        return  employeeCategories.stream().map(employeeCategory -> mapToDto(employeeCategory))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeCategoryDto findEmployeeCategoryById(Long id) {
        EmployeeCategory employeeCategory = employeeCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeCategory", "id", id));
        return mapToDto(employeeCategory);
    }

    @Override
    public EmployeeCategoryDto updateEmployeeCategory(EmployeeCategoryDto employeeCategoryDto, Long id) {
        EmployeeCategory employeeCategoryFromDb = employeeCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeCategory", "id", id));

        Optional<EmployeeCategory> categoryOptional = employeeCategoryRepository.findByName(employeeCategoryDto.getName());
        if (categoryOptional.isPresent()) {
            throw new PayrollException(HttpStatus.CONFLICT, "employee category already exists");
        }

        employeeCategoryFromDb.setName(employeeCategoryDto.getName());
        employeeCategoryFromDb.setDescription(employeeCategoryDto.getDescription());

        EmployeeCategory updatedCategory = employeeCategoryRepository.save(employeeCategoryFromDb);
        return mapToDto(updatedCategory);

    }

    @Override
    public String deleteEmployeeCategory(Long id) {
        EmployeeCategory employeeCategoryFromDb = employeeCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeCategory", "id", id));
        employeeCategoryRepository.deleteById(id);
        return "Employee Category deleted successfully";
    }


    private EmployeeCategoryDto mapToDto(EmployeeCategory employeeCategory) {
        EmployeeCategoryDto employeeCategoryDto = mapper.map(employeeCategory, EmployeeCategoryDto.class);
        return employeeCategoryDto;
    }

    private EmployeeCategory mapToEntity(EmployeeCategoryDto employeeCategoryDto) {
        EmployeeCategory employeeCategory = mapper.map(employeeCategoryDto, EmployeeCategory.class);
        return employeeCategory;
    }
}
