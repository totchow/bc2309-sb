package com.vtxlab.bootcamp.bccryptocoingecko.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.annotation.CoinIdCheck;
import com.vtxlab.bootcamp.bccryptocoingecko.annotation.CurrencyCheck;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;

@Validated
public interface MarketOperation {
  
  @CrossOrigin
  @GetMapping(value = "/coins")
  @ResponseStatus(value = HttpStatus.OK) 
  ApiResponse<List<CoinDTO>> getCoins(@CurrencyCheck @RequestParam(required = true) String currency,
    @CoinIdCheck @RequestParam(value = "ids", required = false, defaultValue = "") List<String> coinIds)
    throws JsonProcessingException;
}
