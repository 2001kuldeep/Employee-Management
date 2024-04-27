package com.magic.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magic.models.TimeInfo;

public interface TimeInfoRepository extends JpaRepository<TimeInfo, String> {

	@Query("SELECT t FROM TimeInfo t WHERE t.employee.empid = :empid")
	List<TimeInfo> findByEmployeeEmpid(@Param("empid") String empid);

	List<TimeInfo> findAllByDate(LocalDate now);
	
    List<TimeInfo> findByDateBetween(LocalDate startDate, LocalDate endDate);

	List<TimeInfo> findByDate(LocalDate date);
	
    @Query("SELECT t FROM TimeInfo t WHERE t.employee.empid=:empid and t.date=:date")
	List<TimeInfo> findByDateAndEmpid(@Param("empid")String empid,@Param("date") LocalDate date);
    
    @Query("SELECT t FROM TimeInfo t WHERE t.employee.empid=:empid and t.date between :startDate and :endDate")
	List<TimeInfo> findByDateBetweenAndEmpid(@Param("empid")String empid,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);



}
