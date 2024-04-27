package com.magic.handlers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.magic.exceptions.DateGreaterThanTodayException;
import com.magic.exceptions.EmployeeNotFoundException;
import com.magic.exceptions.ResponseError;
import com.magic.exceptions.StartDateGreaterThanEndDateException;
import com.magic.exceptions.TimeInfoException;

@RestControllerAdvice
public class ExceptionAndValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult br = ex.getBindingResult();
		List<ObjectError> errors = br.getAllErrors();
		List<String> list = new ArrayList<>();
		for (ObjectError e : errors) {
			list.add(e.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"message\" : \"Invalid request payload\"");
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleExceptionNotFoundException(EmployeeNotFoundException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		ResponseError responseError = new ResponseError("No record Found", list);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);

	}
	@ExceptionHandler(TimeInfoException.class)
	public ResponseEntity<Object> handleTimeInfoException(TimeInfoException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		ResponseError responseError = new ResponseError("Not allowed", list);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);

	}
	@ExceptionHandler(DateGreaterThanTodayException.class)
	public ResponseEntity<Object> handleDateGreaterThanTodayException(DateGreaterThanTodayException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		ResponseError responseError = new ResponseError("Not allowed", list);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);

	}
	
	@ExceptionHandler(StartDateGreaterThanEndDateException.class)
	public ResponseEntity<Object> handleStartDateGreaterThanEndDateException(StartDateGreaterThanEndDateException ex) {
		List<String> list = new ArrayList<>();
		list.add(ex.getMessage());
		ResponseError responseError = new ResponseError("Not allowed", list);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);

	}

}
