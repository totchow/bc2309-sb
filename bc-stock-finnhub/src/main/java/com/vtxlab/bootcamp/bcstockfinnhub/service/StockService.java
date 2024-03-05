package com.vtxlab.bootcamp.bcstockfinnhub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Profile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Quote;

public interface StockService {
  
  Quote getQuote(String symbol) throws JsonProcessingException;

  Profile2 getProfile2(String symbol) throws JsonProcessingException;
}
