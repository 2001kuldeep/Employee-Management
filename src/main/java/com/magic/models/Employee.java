package com.magic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Employee")
public class Employee {
	
	@Id
	@GeneratedValue(generator = "employee_sequence_generator")
	@GenericGenerator(name = "employee_sequence_generator", strategy = "com.magic.models.EmployeeSequenceGenerator")
	@Column(name = "emplpoyeeId", length = 5)
	private String empid;
	
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Only Alphabets Allowed")
	@Column(name = "first", length = 50)
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Only Alphabets Allowed")
	@Column(name = "last", length = 50)
	private String lastName;
	
	@Pattern(regexp = "^(?i)(Sales|Marketing|Accounts|Human Resource)$", message = "Only Sales, Marketing, Accounts, and Human Resource departments are allowed.")
	@Column(name = "department", length = 50)
	private String department;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
