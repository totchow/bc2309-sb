package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.bootcamp.bccryptocoingecko.config.AppStartRunner;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.request.CoinIdDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoinIdValidator implements ConstraintValidator<CoinIdCheck, List<CoinIdDTO>> {

  @Autowired
  AppStartRunner appStartRunner;

  @Override
  public boolean isValid(List<CoinIdDTO> coinIdDTOs, ConstraintValidatorContext context) {

    if (coinIdDTOs == null)
    return true;
    
    List<String> coinIdLists = appStartRunner.getCoinIds();

    // check if every single input id is valid
    for (CoinIdDTO dto : coinIdDTOs) {
      if (!coinIdLists.contains(dto.getCoinId()))
        return false;
    }

    return true;
       
  }
}
  

