package com.vtxlab.bootcamp.bootcampsbcalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vtxlab.bootcamp.infra.InvalidResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(CustomBusException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST) // http status code
  public InvalidResponse InvalidInputExceptionHandler() {
    return InvalidResponse.builder().code(9).message("Invalid Input.").build();

  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST) // http status code
  public InvalidResponse NPEHandler() {
    return InvalidResponse.builder().code(9).message("Invalid Input.").build();

  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST) // http status code
  public InvalidResponse NFEHandler() {
    return InvalidResponse.builder().code(9).message("Invalid Input.").build();
  }

  @ExceptionHandler(ArithmeticException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST) // http status code
  public InvalidResponse AriHandler() {
    return InvalidResponse.builder().code(9).message("Invalid Input.").build();
  }
}
