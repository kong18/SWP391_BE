package com.FPTU.exceptions;

import com.FPTU.controller.RegistrationController;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = RegistrationController.class)
public class UserControllerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  ResponseEntity<ApiExceptionResponse> handleUserException(
      UserNotFoundException exception) {

    final ApiExceptionResponse response = new ApiExceptionResponse(
        exception.getErrorMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

    return ResponseEntity.status(response.getStatus()).body(response);
  }

}
