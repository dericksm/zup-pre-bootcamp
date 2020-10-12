package com.derick.zupbootcamp.controllers.exceptions;

import com.derick.zupbootcamp.services.exceptions.DataIntegrityException;
import com.derick.zupbootcamp.services.exceptions.InvalidDataException;
import com.derick.zupbootcamp.services.exceptions.ObjectNotFoundException;
import com.derick.zupbootcamp.services.exceptions.TokenValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest httpServletRequest){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> objectNotFound(DataIntegrityException ex, HttpServletRequest httpServletRequest){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardError> invalidData(InvalidDataException ex, HttpServletRequest httpServletRequest){
        StandardError error = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), System.currentTimeMillis(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis(), httpServletRequest.getRequestURI());

        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            error.addError(e.getField(), e.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<StandardError> tokenValidationException(TokenValidationException ex, HttpServletRequest httpServletRequest){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
