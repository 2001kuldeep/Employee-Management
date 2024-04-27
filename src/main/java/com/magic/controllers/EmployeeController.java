package com.magic.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.magic.models.Employee;
import com.magic.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// Add an employee
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	// Get a list of all employees
	@GetMapping("/list")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}

	// Get an employee by employee ID
	@GetMapping("/{empid}")
	public ResponseEntity<Employee> getEmployeeByEmpId(@Valid @PathVariable String empid) {
		Employee employee = employeeService.getEmployeeByEmpId(empid);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	// Get employees by department
	@GetMapping("/{department}/list")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment(@Valid @PathVariable String department) {
		List<Employee> employees = employeeService.getEmployeesByDepartment(department);
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}

	// Delete an employee
	@DeleteMapping("/{empid}/delete")
	public ResponseEntity<Employee> deleteEmployee(@Valid @PathVariable String empid) {
		Employee employee = employeeService.deleteEmployee(empid);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	// Update employee department
	@PutMapping("/empid/update")
	public ResponseEntity<Employee> updateEmployeeDepartment(@Valid @PathVariable String empid,
			@Valid @RequestBody Employee updatedEmployee) {
		Employee employee = employeeService.updateEmployeeDepartment(updatedEmployee, empid);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

}
