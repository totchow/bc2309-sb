package com.vtxlab.bootcamp.bcstockfinnhub.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SymbolValidator.class)
public @interface SymbolCheck {
  

  public String message()

  // error message
  default "Invalid Stock Symbol. Please use a valid symbol to try again.";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
}
