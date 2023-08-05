package com.interswitch.employeeportal.controller;

import com.interswitch.employeeportal.dto.EmployeeCategoryDto;
import com.interswitch.employeeportal.entity.EmployeeCategory;
import com.interswitch.employeeportal.service.EmployeeCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-categories")
public class EmployeeCategoryController {

    private final EmployeeCategoryService employeeCategoryService;

    public EmployeeCategoryController(EmployeeCategoryService employeeCategoryService) {
        this.employeeCategoryService = employeeCategoryService;
    }

    @PostMapping
    public ResponseEntity<EmployeeCategoryDto> createEmployeeCategory(@Valid @RequestBody EmployeeCategoryDto employeeCategoryDto) {
        return new ResponseEntity<>(employeeCategoryService.createEmployeeCategory(employeeCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeCategoryDto>> getAllEmployeeCategories() {
        return ResponseEntity.ok(employeeCategoryService.findAllEmployeeCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeCategoryDto> getEmployeeCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeCategoryService.findEmployeeCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeCategoryDto> updateEmployeeCategory(@Valid @RequestBody EmployeeCategoryDto employeeCategoryDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeCategoryService.updateEmployeeCategory(employeeCategoryDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeCategoryService.deleteEmployeeCategory(id));
    }

}
