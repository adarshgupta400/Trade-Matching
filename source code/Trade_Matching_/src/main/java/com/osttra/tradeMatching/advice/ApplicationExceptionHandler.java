package com.osttra.tradeMatching.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.DataNotFound;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.exception.TradeNotFoundException;



@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> resourceNotFoundException(ServiceException ex, WebRequest request) {
	    DataNotFound dtf = new DataNotFound(new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(dtf, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TradeNotFoundException.class)
	public Map<String, String> handleBusinessException(TradeNotFoundException ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(Constants.ERROR, ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoTradeValidation.class)
	public Map<String, String> tradeValidationException(NoTradeValidation ex){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(Constants.ERROR, ex.getMessage());
		return errorMap;
	}
}
