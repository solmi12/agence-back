package com.example.employe.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotNull
    private String firstname;
    @NotNull
    private String lastName;
    @NotNull
    @Email

    private String email;
    private String password;
    private String birthDay;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imageUrl;
    private Role role;


    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Project project;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private  Department department;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<LeaveRequest> leaveRequest;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // You may implement logic to check if the account is non-expired
        // For simplicity, always return true for a non-expired account
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // You may implement logic to check if the account is non-locked
        // For simplicity, always return true for a non-locked account
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // You may implement logic to check if the credentials are non-expired
        // For simplicity, always return true for non-expired credentials
        return true;
    }
    @Override
    public boolean isEnabled() {
        // You may implement logic to check if the user is enabled
        // For simplicity, always return true for enabled users
        return true;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole().name()));
        return authorities;
    }
    public Users(
            String firstname, String lastName, String email, String password, String birthDay,
            byte[] imageUrl, Role role, Project project, Department department,
            List<LeaveRequest> LeaveRequest, List<Work> works) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.imageUrl = imageUrl;
        this.role = role;
        this.project = project;
        this.department = department;
        this.leaveRequest = LeaveRequest;
        this.works = works;
    }
}
