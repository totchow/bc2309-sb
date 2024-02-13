package com.vtxlab.bootcamp.bootcampsbcalculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.vtxlab.bootcamp.bootcampsbcalculator.service.CalculatorService;
import com.vtxlab.bootcamp.infra.Operation;

@Service
public class CalculatorHolder implements CalculatorService{
  
  public String calculate(double x, double y, Operation operator) {
    double result;

    switch (operator) {
      case add:
        result = BigDecimal.valueOf(x).add(BigDecimal.valueOf(y)).doubleValue();
        return String.valueOf(result);
      case sub:
        result = BigDecimal.valueOf(x).subtract(BigDecimal.valueOf(y)).doubleValue();
        return String.valueOf(result);
      case mul:
        result = BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(y)).doubleValue();
        return String.valueOf(result);
      case div:
        result = BigDecimal.valueOf(x)
          .divide(BigDecimal.valueOf(y), 5, RoundingMode.HALF_UP)
          .doubleValue();
        return String.valueOf(result);
      default:
        return null;
    }
  }
}
