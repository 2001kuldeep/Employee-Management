package com.magic.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TimeInfo")
public class TimeInfo {

	@Id
	@GeneratedValue(generator = "time_sequence_generator")
	@GenericGenerator(name = "time_sequence_generator", strategy = "com.magic.models.TimeSequenceGenerator")
	@Column(name = "timeId", length = 5)
	private String tid;

	@NotNull
	@ManyToOne
	private Employee employee;

	@Column(name = "intime")
	private String outTime;

	@Column(name = "outtime")
	private String inTime;

	@Column(name = "totaltime")
	private String totalTime;

	@Column(name = "date")
	private LocalDate date;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
