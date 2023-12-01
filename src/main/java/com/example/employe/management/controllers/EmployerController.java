package com.example.employe.management.controllers;

import com.example.employe.management.dto.EmployerDto;
import com.example.employe.management.dto.UserResponse;
import com.example.employe.management.service.EmployeeService;
import com.example.employe.management.validator.FileValidator;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@RestController
@RequestMapping("/api")
public class EmployerController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private  FileValidator fileValidator;


    private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);

    //send image + json object employee




    @PostMapping(value = "/register_employee")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid EmployerDto employer, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorFields = bindingResult.getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.toList());
                String errorMessage = "Validation errors in fields: " + String.join(", ", errorFields);

                // Log the detailed error message
                logger.error(errorMessage);

                return ResponseEntity.badRequest().body(errorMessage);
            }



            // If it doesn't exist, proceed to register the employee
            employeeService.addEmployer(employer);
            return ResponseEntity.ok("Employee registered successfully.");
        } catch (Exception ex) {
            // Log the exception
            logger.error("An error occurred while registering the employee.", ex);

            // Handle the exception and send an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the employee.");
        }



    }

    @GetMapping("allEmployees")
    public List<UserResponse> getAllEmployees(){
        return employeeService.getAllEmployers();
    }
    @GetMapping("allManagers")
    public List<UserResponse> getAllManagers(){
        return employeeService.getAllManger();
    }
    @DeleteMapping("deleteEmployee/{userId}")
    public void delete(@PathVariable("userId") Integer userId){
        employeeService.deleteEmployer(userId);
    }
    @PutMapping("updateEmployee/{userId}")
    public ResponseEntity<String> update(@PathVariable("userId") Integer userId, @RequestBody @Valid EmployerDto employer, BindingResult bindingResult) throws IOException {

        try {
            logger.info("Received data: {}", employer.toString());

            if (bindingResult.hasErrors()) {
                List<String> errorFields = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getField)
                        .collect(Collectors.toList());
                String errorMessage = "Validation errors in fields: " + String.join(", ", errorFields);
                logger.error(errorMessage);
                return ResponseEntity.badRequest().body(errorMessage);
            }



            employeeService.updateEmployer(userId, employer);

            return ResponseEntity.ok("Request processed successfully");

        } catch (Exception e) {
            // Log the exception for debugging purposes
            logger.error("An error occurred during the update process", e);

            // You can customize the response based on the type of exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the update process");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getEmployee(@PathVariable("userId") Integer userId) {
        logger.debug("Entering getEmployee method with userId: {}", userId);
        try {
            UserResponse userResponse = employeeService.getEmployeeByid(userId);

            if (userResponse != null) {
                return ResponseEntity.ok(userResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found for userId: " + userId);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            logger.error("An error occurred while retrieving employee data", e);

            // Print or log more details about the exception
            e.printStackTrace();

            // You can customize the response based on the type of exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving employee data");
        }
    }




}
