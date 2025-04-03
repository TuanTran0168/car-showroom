package com.tuantran.CarShowroom.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

import java.nio.file.NoSuchFileException;
import java.text.ParseException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        System.out.println("Handle RuntimeException: " + ex.getMessage());
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetail.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.badRequest().body(errorDetail);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("Handle MethodArgumentNotValidException: " + ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        errorDetail.setProperty("errors", errors);
        errorDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(errorDetail);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetail> handleMissingParams(MissingServletRequestParameterException ex) {
        System.out.println("Handle MissingServletRequestParameterException: " + ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getParameterName(), "Parameter [" + ex.getParameterName() + "] is required");

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Missing required parameter");
        errorDetail.setProperty("errors", errors);
        errorDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(errorDetail);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ProblemDetail> handleParseException(ParseException ex) {
        System.out.println("Handle ParseException: " + ex.getMessage());
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.badRequest().body(errorDetail);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        System.out.println("Handle UsernameNotFoundException: " + ex.getMessage());

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        errorDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ProblemDetail> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorDetail);
    }

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(NoSuchFileException.class)
     public ResponseEntity<ProblemDetail> handleNoSuchFileException(NoSuchFileException ex) {
         System.out.println("Handle NoSuchFileException: " + ex.getMessage());
         ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
         errorDetail.setProperty("timestamp", Instant.now());
         return ResponseEntity.badRequest().body(errorDetail);
     }

    @ExceptionHandler(value = {NoResourceFoundException.class})
    public ResponseEntity<ProblemDetail> handleNoResourceFound(NoResourceFoundException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }
}