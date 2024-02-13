package com.vtxlab.bootcamp.bootcampsbcalculator.service;

import com.vtxlab.bootcamp.infra.Operation;

public interface CalculatorService {
  
  String calculate(double x, double y, Operation operator);
}
