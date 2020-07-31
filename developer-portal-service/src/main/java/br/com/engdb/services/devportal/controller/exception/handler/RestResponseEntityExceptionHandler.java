package br.com.engdb.services.devportal.controller.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.engdb.services.commons.exception.BaseException;
import br.com.engdb.services.commons.exception.BusinessException;
import br.com.engdb.services.commons.exception.EntityNotFoundException;
import br.com.engdb.services.commons.exception.EntityNotUniqueException;
import br.com.engdb.services.commons.resources.ErrorMessageResource;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BaseException.class })
	protected ResponseEntity<Object> handleBaseException(BaseException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(ex.getErrorCode(), ex.getErrorMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<Object> handleBaseException(BusinessException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(ex.getErrorCode(), ex.getErrorMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {ex.printStackTrace();
		return handleExceptionInternal(ex, new ErrorMessageResource(500, "Internal Server Error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { AccessDeniedException.class})
	protected ResponseEntity<Object> handleInternalServerError(AccessDeniedException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.FORBIDDEN.value(), "Access denied"), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}
	
	@ExceptionHandler(value = { BadCredentialsException.class})
	protected ResponseEntity<Object> handleInternalServerError(BadCredentialsException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler(value = { UnsupportedOperationException.class})
	protected ResponseEntity<Object> handleInternalServerError(UnsupportedOperationException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.NOT_IMPLEMENTED.value(), "Not Implemented"), new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
	}
	
	@ExceptionHandler(value = { EntityNotFoundException.class})
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.NOT_FOUND.value(), "Not Found"), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { EntityNotUniqueException.class})
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotUniqueException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.CONFLICT.value(), "Not Unique"), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class})
	protected ResponseEntity<Object> handleEntityNotFoundException(ConstraintViolationException ex, WebRequest request) {
		return handleExceptionInternal(ex, new ErrorMessageResource(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
}