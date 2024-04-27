package com.magic.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.models.Employee;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeByEmpId(String empid);

	List<Employee> getEmployeesByDepartment(String department);

	Employee deleteEmployee(String empid);

	Employee updateEmployeeDepartment(Employee updatedEmployee, String empid);
}
