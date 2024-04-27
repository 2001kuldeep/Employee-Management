package com.magic.services.impls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.dtos.TimeInfoMapper;
import com.magic.exceptions.DateGreaterThanTodayException;
import com.magic.exceptions.StartDateGreaterThanEndDateException;
import com.magic.exceptions.TimeInfoException;
import com.magic.models.TimeInfo;
import com.magic.repositories.TimeInfoRepository;
import com.magic.services.TimeInfoService;

@Service
public class TimeInfoServiceImpl implements TimeInfoService {

	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private TimeInfoRepository timeInfoRepository;

	@Override
	public TimeInfo saveTimeInfoInTime(TimeInfo timeInfo) {
		List<TimeInfo> timeInfoList=timeInfoRepository.findByEmployeeEmpid(timeInfo.getEmployee().getEmpid());
		
		if(!timeInfoList.isEmpty()) {
		  if( timeInfoList.get(timeInfoList.size()-1).getOutTime()==null) {
			  throw new TimeInfoException("Employee is already in.");
		  }
			
		}
		timeInfo.setDate(LocalDate.now());
		String currentTime = LocalTime.now().format(formatter);
		timeInfo.setInTime(currentTime);
		timeInfoRepository.save(timeInfo);
		return timeInfo;
	}

	@Override
	public TimeInfo saveTimeInfoOutTime(@Valid String empid) {
		List<TimeInfo> timeInfoList = timeInfoRepository.findByEmployeeEmpid(empid);
		TimeInfo latestTimeInfo = timeInfoList.get(timeInfoList.size() - 1);
		if (latestTimeInfo.getOutTime() != null || timeInfoList.isEmpty()) {
			throw new TimeInfoException("Employee is not in yet.");
		}
		String currentTime = LocalTime.now().format(formatter);
		latestTimeInfo.setOutTime(currentTime);
		Duration duration = Duration.between(LocalTime.parse(latestTimeInfo.getInTime(), formatter),
				LocalTime.parse(latestTimeInfo.getOutTime(), formatter));
		long hours = duration.toHours();
		long minutes = duration.toMinutesPart();
		long seconds = duration.toSecondsPart();
		String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		latestTimeInfo.setTotalTime(formattedDuration);
		timeInfoRepository.save(latestTimeInfo);
		return latestTimeInfo;
	}

	@Override
	public TimeInfo updateTimeInfoEmpId(TimeInfo timeInfo, String tid) {
		TimeInfo oldTimeInfo = timeInfoRepository.findById(tid).orElse(null);
		oldTimeInfo.setEmployee(timeInfo.getEmployee());
		timeInfoRepository.save(oldTimeInfo);
		return oldTimeInfo;
	}

	@Override
	public List<TimeInfo> getTimeInfoByDate() {
		List<TimeInfo> timeInfoList = timeInfoRepository.findAllByDate(LocalDate.now());
		return timeInfoList;
	}
	
	@Override
	public List<TimeInfo> getTimeInfoByDateBetween(LocalDate startDate, LocalDate endDate) {
		if(startDate.isAfter(LocalDate.now())||endDate.isAfter(LocalDate.now())){
			throw new DateGreaterThanTodayException("Entered date should not exceed today");
		}
		if(endDate.isBefore(startDate)){
			System.out.println("inside exception");
			throw new StartDateGreaterThanEndDateException("Start date should be less than or equal to end date");
			
		}
		List<TimeInfo> timeInfoList = timeInfoRepository.findByDateBetween(startDate, endDate);

	    return timeInfoList;
	}

	@Override
	public List<TimeInfo> getTimeInfoByDate(LocalDate date) {
		if(date.isAfter(LocalDate.now())){
			throw new DateGreaterThanTodayException("Entered date should not exceed today");
		}
		List<TimeInfo> timeInfoList = timeInfoRepository.findByDate(date);
	    return timeInfoList;

	}

	@Override
	public List<TimeInfo> getTimeInfoByDate(String empid) {
		List<TimeInfo> timeInfoList = timeInfoRepository.findByDateAndEmpid(empid,LocalDate.now());
		return timeInfoList;
	}

	@Override
	public List<TimeInfo> getTimeInfoByDateBetween(String empid, LocalDate startDate, LocalDate endDate) {
		if(startDate.isAfter(LocalDate.now())||endDate.isAfter(LocalDate.now())){
			throw new DateGreaterThanTodayException("Entered date should not exceed today");
		}
		if(endDate.isBefore(startDate)){
			System.out.println("inside exception");
			throw new StartDateGreaterThanEndDateException("Start date should be less than or equal to end date");
			
		}
		List<TimeInfo> timeInfoList = timeInfoRepository.findByDateBetweenAndEmpid(empid,startDate, endDate);

	    return timeInfoList;
	}

	@Override
	public List<TimeInfo> getTimeInfoByDate(String empid, LocalDate date) {
		if(date.isAfter(LocalDate.now())){
			throw new DateGreaterThanTodayException("Entered date should not exceed today");
		}
		List<TimeInfo> timeInfoList = timeInfoRepository.findByDateAndEmpid(empid,date);
	    return timeInfoList;
	}

}
