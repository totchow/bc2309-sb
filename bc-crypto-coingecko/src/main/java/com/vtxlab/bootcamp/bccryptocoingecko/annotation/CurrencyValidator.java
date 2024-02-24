package com.vtxlab.bootcamp.bccryptocoingecko.annotation;


import com.vtxlab.bootcamp.bccryptocoingecko.infra.Currency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyCheck, String>{
  
  @Override
  public boolean isValid(String currency, ConstraintValidatorContext context) {
  
    return Currency.checkCurrency(currency);
  }

}
