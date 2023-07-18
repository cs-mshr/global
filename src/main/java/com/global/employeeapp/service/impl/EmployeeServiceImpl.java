package com.global.employeeapp.service.impl;

import com.global.employeeapp.model.Employee;
import com.global.employeeapp.repository.EmployeeRepository;
import com.global.employeeapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee add(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Optional<Employee> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public void removeById(UUID id) {
        repository.deleteById(id);
    }

    public Page<Employee> getAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }


}