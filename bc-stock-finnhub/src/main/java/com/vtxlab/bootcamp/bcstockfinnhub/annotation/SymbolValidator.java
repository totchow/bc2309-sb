package com.vtxlab.bootcamp.bcstockfinnhub.annotation;

import com.vtxlab.bootcamp.bcstockfinnhub.infra.StockSymbol;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SymbolValidator implements ConstraintValidator<SymbolCheck, String>{
  
  @Override
  public boolean isValid(String symbol, ConstraintValidatorContext context) {
  
    return StockSymbol.checkStockSymbol(symbol);
  }
}
