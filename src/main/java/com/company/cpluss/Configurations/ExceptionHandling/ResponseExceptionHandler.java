package com.company.cpluss.Configurations.ExceptionHandling;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLInvalidAuthorizationSpecException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity InvalidAuthenticationExceptionHandler(HttpServletRequest httpServletRequest, AuthenticationException authenticationException){
        String errorMessage = authenticationException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationExceptionHandler(HttpServletRequest httpServletRequest, SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        String error = sqlIntegrityConstraintViolationException.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity SQLExceptionHandler(HttpServletRequest httpServletRequest,
                                              SQLException sqlException){
        String errormsg = sqlException.getMessage();
        return ResponseEntity.badRequest().body(errormsg);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> IllegalStateExceptionHandler(HttpServletRequest servletRequest,
                                                               IllegalStateException illegalStateException){
        String error = illegalStateException.getMessage();
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> MethodArgumentTypeMismatchExceptionHandler(HttpServletRequest servletRequest,
                                                                             MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        String error = methodArgumentTypeMismatchException.getMessage();
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SQLInvalidAuthorizationSpecException.class)
    public ResponseEntity SQLInvalidAuthorizationSpecExceptionHandler(HttpServletRequest httpServletRequest,
                                                                      SQLInvalidAuthorizationSpecException invalidAuthorizationSpecException){
        String errormsg = invalidAuthorizationSpecException.getMessage();
        return ResponseEntity.badRequest().body(errormsg);
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
//    public ResponseEntity SQLException(HttpServletRequest httpServletRequest,
//                                       SQLException sqlException){
//        String errormsg = sqlException.getMessage();
//        return ResponseEntity.badRequest().body(errormsg);
//    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity invalidDataAccessApiUsageException(HttpServletRequest httpServletRequest,
                                                             InvalidDataAccessApiUsageException invalidDataAccessApiUsageException){
        String errormsg = invalidDataAccessApiUsageException.getMessage();
        return ResponseEntity.badRequest().body(errormsg);
    }
}
