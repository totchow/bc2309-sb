package com.vtxlab.bootcamp.bcproductdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bcproductdata.infra.ApiResponse;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE) // http status code
  public ApiResponse<Void> restclientExceptionHandler() {
    return ApiResponse.<Void>builder()
      .status(Syscode.REST_CLIENT_EXCEPTION) //
      .data(null) // 
      .build(); 
  }

  @ExceptionHandler(Exception.class) // all exception, order not related
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<String> ExceptionHandler(Exception e) {
    return ApiResponse.<String>builder()
      .status(Syscode.GENERAL_EXCEPTION) //
      .data(e.getMessage()) // 
      .build(); 
  }


  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> npeExceptionHandler() {
    return ApiResponse.<Void>builder()
      .status(Syscode.NULL_POINTER_EXCEPTION) //
      .data(null) // 
      .build(); 
  }
}
