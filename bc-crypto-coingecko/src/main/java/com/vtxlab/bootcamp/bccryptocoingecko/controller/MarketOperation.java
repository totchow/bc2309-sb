package com.vtxlab.bootcamp.bccryptocoingecko.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.annotation.CoinIdCheck;
import com.vtxlab.bootcamp.bccryptocoingecko.annotation.CurrencyCheck;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.request.CoinIdDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;

@Validated
public interface MarketOperation {
  
  @GetMapping(value = "/coins") //request param
  @ResponseStatus(value = HttpStatus.OK) // already throw if any error
  ApiResponse<List<Coin>> getCoins(@CurrencyCheck @RequestParam(required = true) String currency,
    @CoinIdCheck @RequestParam(value = "ids", required = false) List<CoinIdDTO> coinIdDTOs)
    throws JsonProcessingException;
}
