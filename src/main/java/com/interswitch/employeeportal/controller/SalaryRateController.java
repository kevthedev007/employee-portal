package com.interswitch.employeeportal.controller;

import com.interswitch.employeeportal.dto.EmployeeDto;
import com.interswitch.employeeportal.dto.SalaryRateDto;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.entity.SalaryRate;
import com.interswitch.employeeportal.service.SalaryRateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("salary-rates")
public class SalaryRateController {

    private final SalaryRateService salaryRateService;

    public SalaryRateController(SalaryRateService salaryRateService) {
        this.salaryRateService = salaryRateService;
    }

    @PostMapping
    public ResponseEntity<SalaryRateDto> createSalaryRate(@Valid @RequestBody SalaryRateDto salaryRateDto) {
        return new ResponseEntity<>(salaryRateService.createSalaryRate(salaryRateDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SalaryRateDto>> getAllSalaryRates() {
        return ResponseEntity.ok(salaryRateService.getAllSalaryRates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryRateDto> getSalaryRateById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(salaryRateService.getSalaryRateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryRateDto> updateSalaryRate(@Valid @RequestBody SalaryRateDto salaryRateDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(salaryRateService.updateSalaryRate(salaryRateDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalaryRate(@PathVariable Long id) {
        return ResponseEntity.ok(salaryRateService.DeleteSalaryRate(id));
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeesBySalaryRate(@PathVariable Long id) {
        return ResponseEntity.ok(salaryRateService.getEmployeesBySalaryRate(id));
    }
}
