package com.interswitch.employeeportal.dto;

import com.interswitch.employeeportal.entity.Department;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "name must be less than 3 characters")
    private String name;

    @NotEmpty
    private String phone;

    @NotEmpty
    @Size(min = 3, message = "address must not be empty")
    private String address;

    @NotNull
    private LocalDate dob;

    @NotNull
    private LocalDate dateJoined;
    private Long employeeCategoryId;
    private Long departmentId;
}
