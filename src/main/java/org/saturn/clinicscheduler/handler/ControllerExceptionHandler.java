package org.saturn.clinicscheduler.handler;

import org.saturn.clinicscheduler.exception.AppointmentNotFoundException;
import org.saturn.clinicscheduler.exception.BusyPhoneNumberException;
import org.saturn.clinicscheduler.exception.DepartmentHasAlreadyExistedException;
import org.saturn.clinicscheduler.exception.DepartmentNotFoundException;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.saturn.clinicscheduler.exception.PatientNotFoundException;
import org.saturn.clinicscheduler.exception.ScheduleIsBookedException;
import org.saturn.clinicscheduler.exception.ScheduleSlotNotFoundException;
import org.saturn.clinicscheduler.exception.SpecialityAlreadyExistException;
import org.saturn.clinicscheduler.exception.SpecialityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    protected ResponseEntity<Object> handleDoctorNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Doctor with such ID or speciality was not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    protected ResponseEntity<Object> handleDepartmentNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Department with such ID was not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    protected ResponseEntity<Object> handlePatientNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Patient does not exist";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BusyPhoneNumberException.class)
    protected ResponseEntity<Object> handlePatientPhoneNumberBusy(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Phone must by unique";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(SpecialityNotFoundException.class)
    protected ResponseEntity<Object> handleSpecialityNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Speciality with such ID was not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ScheduleSlotNotFoundException.class)
    protected ResponseEntity<Object> handleScheduleNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This time slot is unavailable";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    protected ResponseEntity<Object> handleAppointmentNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Appointment with such ID was not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SpecialityAlreadyExistException.class)
    protected ResponseEntity<Object> handleSpecialityAlreadyExist(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Speciality with such name already exist";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ScheduleIsBookedException.class)
    protected ResponseEntity<Object> handleScheduleIsBooked(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This schedule is booked. You can't change it";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(DepartmentHasAlreadyExistedException.class)
    protected ResponseEntity<Object> handleDepartmentHasAlreadyExisted(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Department with such parameters has already existed";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
