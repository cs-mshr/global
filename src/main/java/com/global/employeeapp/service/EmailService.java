package com.global.employeeapp.service;
// EmailService.java

import com.global.employeeapp.model.Employee;
import com.global.employeeapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailUsername;


    public void sendEmailToManager(Employee newEmployee) {
        String managerEmail = getManagerEmail(newEmployee);

        String subject = "New Employee Addition to your Team";
        String body = newEmployee.getName() + " will now work under you. Mobile number is " +
                newEmployee.getPhoneNumber() + " and email is " + newEmployee.getEmail();


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailUsername);
        message.setTo(managerEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    private String getManagerEmail(Employee employee) {
        Employee manager = employeeRepository.findById(employee.getReportsTo()).orElse(null);
        return manager != null ? manager.getEmail() : "cshekharmshr@gmail.com";
    }
}