package com.vtxlab.bootcamp.bccryptocoingecko.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.request.CoinIdDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;

public interface MarketService {
  
  List<Coin> getCoins(String currency, List<CoinIdDTO> coinIdDTOs) 
    throws JsonProcessingException;
}
