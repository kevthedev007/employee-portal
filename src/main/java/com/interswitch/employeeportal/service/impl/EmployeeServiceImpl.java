package com.interswitch.employeeportal.service.impl;

import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.entity.Department;
import com.interswitch.employeeportal.entity.Employee;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.exception.ResourceNotFoundException;
import com.interswitch.employeeportal.repository.DepartmentRepository;
import com.interswitch.employeeportal.repository.EmployeeCategoryRepository;
import com.interswitch.employeeportal.repository.EmployeeRepository;
import com.interswitch.employeeportal.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeCategoryRepository employeeCategoryRepository;
    private ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeCategoryRepository employeeCategoryRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeCategoryRepository = employeeCategoryRepository;
        this.mapper = mapper;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", employeeDto.getDepartmentId()));

        EmployeeCategory employeeCategory = employeeCategoryRepository.findById(employeeDto.getEmployeeCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee Category", "id", employeeDto.getEmployeeCategoryId()));

        Employee employee = mapToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setEmployeeCategory(employeeCategory);

        Employee employeeFromDb = employeeRepository.save(employee);
        return mapToDto(employeeFromDb);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> mapToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employee.setName(employeeDto.getName());
        employee.setPhone(employeeDto.getPhone());
        employee.setDob(employeeDto.getDob());
        employee.setAddress(employeeDto.getAddress());
        employee.setDateJoined(employeeDto.getDateJoined());


        Employee updateEmployee = employeeRepository.save(employee);
        return mapToDto(employee);
    }

    @Override
    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public List<EmployeeDto> getEmployeesByManager(Long employeeId) {
        Department department = departmentRepository.findByManagerId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "manager id", employeeId));

        List<Employee> employees = employeeRepository.findByDepartmentId(department.getId());

        return employees.stream().map(employee -> mapToDto(employee))
                .collect(Collectors.toList());
    }


    private EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }

}
