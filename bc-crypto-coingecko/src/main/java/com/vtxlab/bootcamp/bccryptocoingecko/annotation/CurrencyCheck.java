package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

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
@Constraint(validatedBy = CurrencyValidator.class)
public @interface CurrencyCheck {
    
  public String message()

  // error message
  default "Invalid currency. Please enter a valid currency to try again.";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
  
}
