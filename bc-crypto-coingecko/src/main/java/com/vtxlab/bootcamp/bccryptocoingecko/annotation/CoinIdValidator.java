package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.vtxlab.bootcamp.bccryptocoingecko.config.AppStartRunner;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoinIdValidator implements ConstraintValidator<CoinIdCheck, List<String>> {

  @Autowired
  AppStartRunner appStartRunner;

  @Override
  public boolean isValid(List<String> ids, ConstraintValidatorContext context) {

    if (ids.size() == 0)
    return true;
    
    List<String> coinIds = appStartRunner.getCoinIds();

    //check if every single input id is valid
    for (String id : ids) {
      if (!coinIds.contains(id))
        return false;
    }

    return true;
       
  }
}
  

