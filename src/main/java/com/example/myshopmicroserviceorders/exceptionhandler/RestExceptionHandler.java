package com.example.myshopmicroserviceorders.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshopmicroserviceorders.exception.NoOrderFoundException;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value=NoOrderFoundException.class)
	public ResponseEntity<ApiError> handleNoProductException()
	{
		ApiError err=new ApiError("Order Microservice:"+400,"No Order Found 1",new Date());
		return new ResponseEntity<ApiError>(err,HttpStatus.BAD_REQUEST);
	}


}
