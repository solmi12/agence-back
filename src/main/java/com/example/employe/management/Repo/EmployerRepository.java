package com.example.employe.management.Repo;

import com.example.employe.management.model.Role;
import com.example.employe.management.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface EmployerRepository extends CrudRepository<Users, Integer> {
    Users findByEmail(java.lang.String email);
    Users findByUserId(Integer userId);
    List<Users> findByRole(Role role);
}
