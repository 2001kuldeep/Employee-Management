package com.magic.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.magic.models.TimeInfo;

public interface TimeInfoService {

	TimeInfo saveTimeInfoInTime(TimeInfo timeInfo);

	TimeInfo saveTimeInfoOutTime(String empid);

	TimeInfo updateTimeInfoEmpId(TimeInfo timeInfo, String tid);

	List<TimeInfo> getTimeInfoByDate();
	
	List<TimeInfo> getTimeInfoByDateBetween(LocalDate startDate, LocalDate endDate);

	List<TimeInfo> getTimeInfoByDate(LocalDate date);

	List<TimeInfo> getTimeInfoByDate(String empid);

	List<TimeInfo> getTimeInfoByDateBetween(String empid, LocalDate startDate, LocalDate endDate);

	List<TimeInfo> getTimeInfoByDate(String empid, LocalDate date);



}
