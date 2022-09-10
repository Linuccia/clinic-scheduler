package org.saturn.clinicscheduler.handler;

import org.saturn.clinicscheduler.exception.DepartmentNotFoundException;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DoctorNotFoundException.class)
    protected ResponseEntity<Object> handleDoctorNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Doctor with such ID was not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    protected ResponseEntity<Object> handleDepartmentNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Department with such ID was not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
