package com.example.learning.commons.security.handler;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TwensNotFoundException.class)
	public ResponseEntity<ErrorObject>handleTwensNotFoundException(TwensNotFoundException ex, WebRequest request){
		ErrorObject errorObject = new ErrorObject ();

		errorObject.setStatusCode (HttpStatus.NOT_FOUND.value());
		errorObject.setMessage (ex.getMessage ());
		errorObject.setTimestamp (new Date ());

		return new ResponseEntity<ErrorObject> (errorObject, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorObject>handleReviewNotFoundException(ReviewNotFoundException ex, WebRequest request){
		ErrorObject errorObject = new ErrorObject ();

		errorObject.setStatusCode (HttpStatus.NOT_FOUND.value());
		errorObject.setMessage (ex.getMessage ());
		errorObject.setTimestamp (new Date ());

		return new ResponseEntity<ErrorObject> (errorObject, HttpStatus.NOT_FOUND);
	}
}
