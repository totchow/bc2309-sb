package com.vtxlab.bootcamp.bootcampsbcalculator.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.bootcamp.bootcampsbcalculator.controller.CalculatorOperation;
import com.vtxlab.bootcamp.bootcampsbcalculator.dto.Input;
import com.vtxlab.bootcamp.bootcampsbcalculator.exception.CustomBusException;
import com.vtxlab.bootcamp.bootcampsbcalculator.service.CalculatorService;
import com.vtxlab.bootcamp.infra.ApiResponse;
import com.vtxlab.bootcamp.infra.Operation;

@RestController
@RequestMapping(value = "cal/api/v1/")
public class CalculatorController implements CalculatorOperation{
  
  @Autowired
  private CalculatorService calservice;

  @Override
  public ApiResponse paramCal(String x, String y, String operation) throws CustomBusException{
    double x1 = Double.parseDouble(x);
    double x2 = Double.parseDouble(y);
    Operation operator = checkOperation(operation);
    String result = calservice.calculate(x1,x2,operator);

    return ApiResponse.builder()
              .x(x)
              .y(y)
              .operation(operation)
              .result(result)
              .build();
  }

  @Override
  public ApiResponse postCal(Input input) throws CustomBusException{
    double x1 = Double.parseDouble(input.getX());
    double x2 = Double.parseDouble(input.getY());
    Operation operator = checkOperation(input.getOperation());


    String result = calservice.calculate(x1,x2,operator);

    return ApiResponse.builder()
              .x(input.getX())
              .y(input.getY())
              .operation(input.getOperation())
              .result(result)
              .build();
  }

  @Override
  public ApiResponse pathCal(String x, String y, String operation) throws CustomBusException{
    double x1 = Double.parseDouble(x);
    double x2 = Double.parseDouble(y);
    Operation operator = checkOperation(operation);
    String result = calservice.calculate(x1,x2,operator);

    return ApiResponse.builder()
              .x(x)
              .y(y)
              .operation(operation)
              .result(result)
              .build();
  }


  public static Operation checkOperation(String operation) throws CustomBusException{
    switch (operation) {
      case "add":
        return Operation.add;
      case "sub":
        return Operation.sub;
      case "mul":
        return Operation.mul;
      case "div":
        return Operation.div;
      default:
        throw new CustomBusException();
    }
  }
}
