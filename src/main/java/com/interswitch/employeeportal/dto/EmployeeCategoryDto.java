package com.interswitch.employeeportal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EmployeeCategoryDto {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "name must not be less than 3 characters")
    private String name;

    @NotEmpty
    private String Description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
