package com.magic.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magic.dtos.TimeInfoDto;
import com.magic.dtos.TimeInfoMapper;
import com.magic.models.TimeInfo;
import com.magic.services.EmployeeService;
import com.magic.services.TimeInfoService;

@RestController
@RequestMapping("employee")
public class TimeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TimeInfoService timeInfoService;
	
	@Autowired
	private TimeInfoMapper timeInfoMapper;

	@PostMapping("/intime/record")
	public ResponseEntity<TimeInfoDto> addTimeInfoInTime(@Valid @RequestBody TimeInfoDto TimeInfoDto) {
		TimeInfo timeInfo = timeInfoMapper.toEntity(TimeInfoDto);
		TimeInfo returnedTimeInfo = timeInfoService.saveTimeInfoInTime(timeInfo);
		TimeInfoDto createdTimeInfoDto = timeInfoMapper.toDto(returnedTimeInfo);
		return ResponseEntity.status(HttpStatus.OK).body(createdTimeInfoDto);
	}
	
	@PutMapping("/intime/{tid}/update")
	public ResponseEntity<TimeInfoDto> updateTimeInfoInTime(@Valid @PathVariable String tid , @RequestBody TimeInfoDto TimeInfoDto ){
		return updateEmpId(tid,TimeInfoDto);
	}

	@PutMapping("/outtime/{empid}/record")
	public ResponseEntity<TimeInfoDto> addTimeInfoOutTime(@Valid @RequestBody @PathVariable String empid) {
		TimeInfo returnedTimeInfo = timeInfoService.saveTimeInfoOutTime(empid);
		TimeInfoDto updatedTimeInfoDto = timeInfoMapper.toDto(returnedTimeInfo);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTimeInfoDto);
	}

	@PutMapping("/outtime/{tid}/update")
	public ResponseEntity<TimeInfoDto> updateEmpId(@Valid @PathVariable String tid,@RequestBody TimeInfoDto TimeInfoDto) {
		TimeInfo timeInfo = timeInfoMapper.toEntity(TimeInfoDto);
		TimeInfo returnedTimeInfo = timeInfoService.updateTimeInfoEmpId(timeInfo, tid);
		TimeInfoDto updatedTimeInfoDto = timeInfoMapper.toDto(returnedTimeInfo);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTimeInfoDto);
	}

	@GetMapping("/currentdate/reports")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDate() {
		List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDate();
		List<TimeInfoDto> timeInfosDto = new ArrayList<>();
		for (TimeInfo t : timeInfo) {
			TimeInfoDto TimeInfoDto = timeInfoMapper.toDto(t);
			timeInfosDto.add(TimeInfoDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(timeInfosDto);
	}
	
	@GetMapping("/datebetween/reports")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDateBetween(
	    @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
	    

	    List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDateBetween(startDate, endDate);
	    List<TimeInfoDto> timeInfosDto = new ArrayList<>();
	    for (TimeInfo t : timeInfo) {
	        TimeInfoDto timeInfoDto = timeInfoMapper.toDto(t);
	        timeInfosDto.add(timeInfoDto);
	    }
	    return ResponseEntity.ok(timeInfosDto);
	}
	
	@GetMapping("/anydate/report")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDate(
	    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	    
	    List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDate(date);
	    List<TimeInfoDto> timeInfosDto = new ArrayList<>();
	    for (TimeInfo t : timeInfo) {
	        TimeInfoDto timeInfoDto = timeInfoMapper.toDto(t);
	        timeInfosDto.add(timeInfoDto);
	    }
	    return ResponseEntity.ok(timeInfosDto);
	}

	@GetMapping("/{empid}/currentdate/reports")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDate(@PathVariable String empid  ) {
		List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDate(empid);
		List<TimeInfoDto> timeInfosDto = new ArrayList<>();
		for (TimeInfo t : timeInfo) {
			TimeInfoDto TimeInfoDto = timeInfoMapper.toDto(t);
			timeInfosDto.add(TimeInfoDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(timeInfosDto);
	}
	
	@GetMapping("/{empid}/datebetween/reports")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDateBetween(@PathVariable String empid,
	    @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
	    

	    List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDateBetween(empid,startDate, endDate);
	    List<TimeInfoDto> timeInfosDto = new ArrayList<>();
	    for (TimeInfo t : timeInfo) {
	        TimeInfoDto timeInfoDto = timeInfoMapper.toDto(t);
	        timeInfosDto.add(timeInfoDto);
	    }
	    return ResponseEntity.ok(timeInfosDto);
	}
	
	@GetMapping("{empid}/anydate/reports")
	public ResponseEntity<List<TimeInfoDto>> getTimeInfoByDate(@PathVariable String empid,
	    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	    
	    List<TimeInfo> timeInfo = timeInfoService.getTimeInfoByDate(empid,date);
	    List<TimeInfoDto> timeInfosDto = new ArrayList<>();
	    for (TimeInfo t : timeInfo) {
	        TimeInfoDto timeInfoDto = timeInfoMapper.toDto(t);
	        timeInfosDto.add(timeInfoDto);
	    }
	    return ResponseEntity.ok(timeInfosDto);
	}

}
