package com.global.employeeapp.controller;

import com.global.employeeapp.model.Employee;
import com.global.employeeapp.service.EmailService;
import com.global.employeeapp.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    private final EmailService emailService;

    //------------------------------Email-Service----------------------------//
    //------------------------------Email-Service----------------------------//
    @PostMapping
    public ResponseEntity<UUID> addEmployee(
            @RequestBody Employee employee
    ) {
        employee.setId(java.util.UUID.randomUUID());
        employeeService.add(employee);

        if (employee.getReportsTo() != null) {
            emailService.sendEmailToManager(employee);
        }

        return new ResponseEntity<>(employee.getId(), HttpStatus.CREATED);
    }


    //--------------------Paging-Sorting------------------//
    //--------------------Paging-Sorting------------------//
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployeesWithPaginationAndSorting(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "employeeName") String sortField
    ) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = employeeService.getAllPage(pageable);

        if (employeePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Employee> employees = employeePage.getContent();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //    //---------------------Nth Level Manger------------------//
//    //---------------------Nth Level Manger------------------//
    @GetMapping("/{id}/manager")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable UUID id,
            @RequestParam(name = "n", defaultValue = "1") int n
    ) {
        Employee employee = employeeService.getById(id).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UUID reportsToId = employee.getReportsTo();
        for (int i = 0 ;reportsToId != null && i < n; i++) {
            employee = employeeService.getById(reportsToId).orElse(null);
            reportsToId = employee != null ? employee.getReportsTo() : null;
        }

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }



//    ---------------------ManagementOperation----------------------
//    ---------------------ManagementOperation----------------------

//    @PostMapping
//    public ResponseEntity<UUID> addEmployee(@RequestBody Employee employee) {
//        employee.setId(java.util.UUID.randomUUID());
//
//        employeeRepository.save(employee);
//        return new ResponseEntity<>(employee.getId(), HttpStatus.CREATED);
////        return ResponseEntity.ok(employee.getId());
//    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getByID(
            @PathVariable UUID id
    ){
        Employee employee = employeeService.getById(id).orElse(null);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.removeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/profile-photo/{id}")
    public ResponseEntity<Employee> updatePhoto(
            @RequestParam("profilePhoto") MultipartFile profilePhoto,
            @PathVariable UUID id
    ) throws IOException {

        String Image = Base64.getEncoder().encodeToString(profilePhoto.getBytes());

        Employee employee = getByID(id).getBody();

        //Profile photo updated
        employee.setProfileImage(Image);

        //updated in database
        employeeService.add(employee);

        return ResponseEntity.ok(employee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(
            @PathVariable UUID id,
            @RequestBody Employee updatedEmployee
    ) {
        Employee employee = employeeService.getById(id).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee = Employee.builder()
                .id(id)
                .name(updatedEmployee.getName())
                .phoneNumber(updatedEmployee.getPhoneNumber())
                .email(updatedEmployee.getEmail())
                .reportsTo(updatedEmployee.getReportsTo())
                .profileImage(updatedEmployee.getProfileImage())
                .build();

        employeeService.add(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}