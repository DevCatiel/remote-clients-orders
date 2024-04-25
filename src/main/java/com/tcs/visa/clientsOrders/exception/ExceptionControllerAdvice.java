package com.tcs.visa.clientsOrders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException ex) {
	    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal server error: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}