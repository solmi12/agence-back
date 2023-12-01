package com.example.employe.management;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Slf4j
@EnableWebSecurity
@EnableWebMvc
@EnableRedisRepositories
@SpringBootApplication
@EntityScan(basePackages = "com.example.employe.management.model")
public class EmployeManagementApplication {

	public static void main(String[] args) {
		log.info("Starting Employee Management Application");
		SpringApplication.run(EmployeManagementApplication.class, args);
		log.info("Employee Management Application started successfully");
		System.out.println("hello world");
	}
}
