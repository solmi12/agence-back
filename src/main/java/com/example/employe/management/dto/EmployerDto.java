package com.example.employe.management.dto;


import com.example.employe.management.model.LeaveRequest;
import com.example.employe.management.model.Work;
import jakarta.validation.constraints.*;

import java.util.List;


public class EmployerDto {

    private Integer userId;
   @NotNull

    private String firstname;
    @NotBlank

    private String lastName;
   @Email
   @NotBlank

    private String email;
    @Size(min =3)
    @NotBlank

    private String password;
    @NotNull

    private String birthDay;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private List<LeaveRequest> leaveRequest;

    public List<LeaveRequest> getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(List<LeaveRequest> leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    private List<Work> works;
    @NotBlank

    private String role;



    public EmployerDto() {
    }




    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
