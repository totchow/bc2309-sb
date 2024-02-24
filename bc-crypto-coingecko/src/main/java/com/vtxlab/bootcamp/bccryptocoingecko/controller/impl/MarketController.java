package com.vtxlab.bootcamp.bccryptocoingecko.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.MarketOperation;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.MarketService;

@RestController
@RequestMapping(value = "crypto/coingecko/api/v1")
public class MarketController implements MarketOperation{
  
  @Autowired
  MarketService marketService;

  @Override
  public ApiResponse<List<Coin>> getCoins(String currency, List<String> coinIds) 
  throws JsonProcessingException{
    
    List<Coin> coins = marketService.getCoins(currency,coinIds);

    return ApiResponse.<List<Coin>>builder().status(Syscode.OK).data(coins).build();

  }

}
