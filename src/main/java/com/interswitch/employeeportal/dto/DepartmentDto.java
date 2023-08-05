package com.interswitch.employeeportal.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "department name must not be less than 3 characters")
    private String name;

    @NotEmpty
    @Size(min = 3, message = "description must not be less than 3 characters")
    private String description;

    private Long managerId;
    private List<EmployeeDto> employees;
}
