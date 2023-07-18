package com.global.employeeapp.repository;


import com.global.employeeapp.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EmployeeRepository extends MongoRepository<Employee, UUID> {
}