package com.magic.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.models.Employee;
import com.magic.repositories.EmployeeRepository;
import com.magic.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee getEmployeeByEmpId(String empid) {
		Employee employee = employeeRepository.findById(empid).orElse(null);
		return employee;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		List<Employee> employees = employeeRepository.findByDepartment(department);
		return employees;
	}

	@Override
	public Employee deleteEmployee(String empid) {
		Employee employee = employeeRepository.findById(empid).orElse(null);
//		if (employee==null){
//			throw  new Exception("No Employee with EmpId "+empid);
//		}
		employeeRepository.deleteById(empid);
		return employee;
	}

	@Override
	public Employee updateEmployeeDepartment(Employee updatedEmployee, String empid) {
		updatedEmployee.setEmpid(empid);
		employeeRepository.save(updatedEmployee);
		return updatedEmployee;
	}

}
