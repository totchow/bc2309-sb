package com.vtxlab.bootcamp.bootcampsbcalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.vtxlab.bootcamp.bootcampsbcalculator.dto.Input;
import com.vtxlab.bootcamp.bootcampsbcalculator.exception.CustomBusException;
import com.vtxlab.bootcamp.infra.ApiResponse;

public interface CalculatorOperation {
  
  @GetMapping(value = "/get/")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse paramCal(@RequestParam(value = "x") String x, 
     @RequestParam(value = "y") String y,
     @RequestParam(value = "operation") String operation) throws CustomBusException;
  
  @PostMapping(value = "/post")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse postCal(@RequestBody Input input) throws CustomBusException;

  @GetMapping(value = "/get/{x}/{y}/{operation}")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse pathCal(@PathVariable String x,
      @PathVariable String y,
      @PathVariable String operation) throws CustomBusException;

}
