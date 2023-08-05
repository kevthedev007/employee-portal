package com.interswitch.employeeportal.service.impl;

import com.interswitch.employeeportal.dto.DepartmentDto;
import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.entity.Department;
import com.interswitch.employeeportal.entity.Employee;
import com.interswitch.employeeportal.exception.ResourceNotFoundException;
import com.interswitch.employeeportal.repository.DepartmentRepository;
import com.interswitch.employeeportal.repository.EmployeeRepository;
import com.interswitch.employeeportal.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Optional<Long> managerId = Optional.ofNullable(departmentDto.getManagerId());
        Employee employee = null;

        if (managerId.isPresent()) {
            employee = employeeRepository.findById(managerId.get())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", managerId));
        }

        Department department = mapToEntity(departmentDto);
        department.setManager(employee);

        Department departmentFromDb = departmentRepository.save(department);

        return mapToDto(departmentFromDb);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> mapToDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        Optional<Long> managerId = Optional.ofNullable(departmentDto.getManagerId());
        Employee employee = null;

        if (managerId.isPresent()) {
            employee = employeeRepository.findById(managerId.get())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", managerId));
        }

        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department.setManager(employee);

        Department departmentFromDb = departmentRepository.save(department);

        return mapToDto(department);
    }

    @Override
    public String deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        departmentRepository.deleteById(id);
        return "Department Deleted successfully";
    }

    @Override
    public List<EmployeeDto> findEmployeesByDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        List<Employee> employees = employeeRepository.findByDepartmentId(id);

        return employees.stream().map(employee -> mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }


    private DepartmentDto mapToDto(Department department) {
        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    private Department mapToEntity(DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        return department;
    }

    private EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
