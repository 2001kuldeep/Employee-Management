package com.magic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	List<Employee> findByDepartment(String department);

}
