package com.example.employe.management.dto;

import com.example.employe.management.model.Department;
import com.example.employe.management.model.LeaveRequest;
import com.example.employe.management.model.Project;
import com.example.employe.management.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private int userId ;
    private String firstname;
    private String lastName;
    private String email;

    private Role role;
    private String birthDay;
    private byte[] imageUrl;



    private Project project;

    private LeaveRequest leaveRequest;
    private Department department;
}
