package com.example.employe.management.service;

import com.example.employe.management.Repo.EmployerRepository;
import com.example.employe.management.controllers.EmployerController;
import com.example.employe.management.model.LeaveRequest;
import com.example.employe.management.model.Work;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.employe.management.dto.EmployerDto;
import com.example.employe.management.dto.UserResponse;
import com.example.employe.management.exception.UserFoundException;
import com.example.employe.management.model.Role;
import com.example.employe.management.model.Users;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class EmployeeService implements UserDetailsService {

    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployerRepository employerRepository, PasswordEncoder passwordEncoder) {
        this.employerRepository = employerRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public Users addEmployer(EmployerDto employer) throws IOException {
        Users userExist= employerRepository.findByEmail(employer.getEmail());
        if (userExist!=null){
            throw new UserFoundException("account  exist");
        }
       Users newEmployer =new Users();
       String roleString=employer.getRole();
        System.out.println("#########################");
        System.out.println(roleString);
         if (roleString.equals("EMPLOYER")) {
            newEmployer.setRole(Role.EMPLOYER);
        } else if (roleString.equals("MANAGER")) {
            newEmployer.setRole(Role.MANAGER);
        } else {

            throw new IllegalArgumentException("Invalid role value: " + roleString);
        }

       newEmployer.setEmail(employer.getEmail());
       newEmployer.setFirstname(employer.getFirstname());
       newEmployer.setLastName(employer.getLastName());





       newEmployer.setImageUrl(decodeBase64Image(employer.getImageUrl()));
       newEmployer.setBirthDay(employer.getBirthDay());

       newEmployer.setPassword(passwordEncoder.encode(employer.getPassword()));
     return   employerRepository.save(newEmployer);
    }


    @Override
    public UserDetails loadUserByUsername(java.lang.String username) throws UsernameNotFoundException {
        Users user= employerRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("user with email "+username+" not exist");
        }else {
            //log.info("user exist in database");
        }


        return new User(user.getEmail(),user.getPassword(),user.getAuthorities());
    }

    public List<UserResponse> getAllEmployers() {
        List<Users> users = employerRepository.findByRole(Role.EMPLOYER);
        List<UserResponse> employers = new ArrayList<>();

        users.forEach((u) -> {
            UserResponse e = new UserResponse();

            // Check if userId is not null before using it
            Integer userId = u.getUserId();
            if (userId != null) {
                e.setUserId(userId);
            } else {
                // Handle the case where userId is null
                // You might want to log a warning or handle it differently based on your requirements
            }

            // Set other properties
            e.setDepartment(u.getDepartment());
            e.setProject(u.getProject());
            e.setBirthDay(u.getBirthDay());
            e.setEmail(u.getEmail());
            e.setFirstname(u.getFirstname());
            e.setLastName(u.getLastName());


            employers.add(e);
        });

        return employers;
    }
    public List<UserResponse> getAllManger(){
        List<Users> users=employerRepository.findByRole(Role.MANAGER);
        List<UserResponse> managers = new ArrayList<>();
        users.forEach((u)->{
            UserResponse e=new UserResponse();
            e.setDepartment(u.getDepartment());
            e.setProject(u.getProject());
            e.setBirthDay(u.getBirthDay());
            e.setEmail(u.getEmail());

            e.setFirstname(u.getFirstname());
            e.setLastName(u.getLastName());

            managers.add(e);


        });
        return managers;
    }
    public void deleteEmployer(Integer id){
        employerRepository.deleteById(id);
    }



    public void updateEmployer(Integer userId, EmployerDto employer) throws IOException {
        logger.info("Updating employer with userId: {}", userId);

        Users userExist = employerRepository.findByUserId(userId);
        if (userExist == null) {
            logger.error("User not found with userId: {}", userId);
            throw new UserFoundException("User not found");
        }

        // Update the existing entity instead of creating a new one
        String roleString = employer.getRole();
        if ("EMPLOYER".equals(roleString)) {
            userExist.setRole(Role.EMPLOYER);
        } else if ("MANAGER".equals(roleString)) {
            userExist.setRole(Role.MANAGER);
        } else {
            userExist.setRole(Role.ADMIN);
        }

        userExist.setEmail(employer.getEmail());
        userExist.setFirstname(employer.getFirstname());
        userExist.setLastName(employer.getLastName());
        userExist.setImageUrl(decodeBase64Image(employer.getImageUrl()));
        userExist.setBirthDay(employer.getBirthDay());
        userExist.setPassword(passwordEncoder.encode(employer.getPassword()));

        // Update leaveRequest if not null
        if (employer.getLeaveRequest() != null) {
            userExist.getLeaveRequest().clear(); // Clear existing leave requests
            userExist.getLeaveRequest().addAll(employer.getLeaveRequest());
            for (LeaveRequest leaveRequest : userExist.getLeaveRequest()) {
                leaveRequest.setEmployer(userExist);
            }
        }

        // Update works if not null
        if (employer.getWorks() != null) {
            userExist.getWorks().clear(); // Clear existing works
            userExist.getWorks().addAll(employer.getWorks());
            for (Work work : userExist.getWorks()) {
                work.setEmployer(userExist);
            }
        }

        // Log the updated employer information
        logger.info("Updated employer details: {}", userExist);

        employerRepository.save(userExist);
        logger.info("Employer updated successfully");
    }

    private byte[] decodeBase64Image(String base64Image) {
        if (base64Image != null) {
            try {
                // Remove the prefix if present
                String base64Data = base64Image.replaceAll("data:image/jpeg;base64,", "");
                return Base64.getDecoder().decode(base64Data);
            } catch (IllegalArgumentException e) {
                // Log the problematic base64Image value

                throw e; // rethrow the exception or handle it as needed
            }
        }
        return null;
    }



    public Users getUserByemail(String email){
        return employerRepository.findByEmail(email);
  }

    public UserResponse getEmployeeByid(Integer userId) {
        Optional<Users> emplOpt=employerRepository.findById(userId);
        if (emplOpt.isPresent()){
            Users empl=emplOpt.get();
            UserResponse resp=new UserResponse();
            resp.setFirstname(empl.getFirstname());
            resp.setLastName(empl.getLastName());
            resp.setProject(empl.getProject());
            resp.setDepartment(empl.getDepartment());
            resp.setEmail(empl.getEmail());
            resp.setRole(empl.getRole());
            resp.setBirthDay(empl.getBirthDay());
            resp.setUserId(empl.getUserId());
          resp.setImageUrl(empl.getImageUrl());
            return resp;



        }
        throw new UserFoundException("user with id= "+userId+" not found");
    }


    public Integer NumberOfemployees(){
        return getAllEmployers().toArray().length;
    }
}
