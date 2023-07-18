package com.global.employeeapp.service;

import com.global.employeeapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    Employee add(Employee employee);

    Optional<Employee> getById(UUID id);

    List<Employee> getAll();

    void removeById(UUID id);

    Page<Employee> getAllPage(Pageable pageable);
}