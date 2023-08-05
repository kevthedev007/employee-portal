package com.interswitch.employeeportal.dto;

import com.interswitch.employeeportal.entity.EmployeeCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalaryRateDto {
    private Long id;

    @NotNull
    private int level;

    @NotNull
    private Double amount;
    private Long employeeCategoryId;
}
