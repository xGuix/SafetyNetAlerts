package com.safetynet.alerts.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler 
{
	private static Logger logger = LogManager.getLogger("HandlerException");
	
	  /**
	   * Custom exception throw by error
	   * then return a readeable response
	   *
	   * @param  NotFountException.
	   * @return ResponseEntity with {@link HttpStatus} NOT_FOUND.
	   */
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> personNotFoundException(NotFoundException e){
        logger.error("Person Not Found");
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}