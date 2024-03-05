package com.vtxlab.bootcamp.bcstockfinnhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.annotation.SymbolCheck;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.Profile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.QuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;

@Validated
public interface StockOperation {

  @GetMapping(value = "/quote")
  @ResponseStatus(value = HttpStatus.OK) 
  ApiResponse<QuoteDTO> getQuote(@SymbolCheck @RequestParam(required = true) String symbol)
    throws JsonProcessingException;

  @GetMapping(value = "/profile2")
  @ResponseStatus(value = HttpStatus.OK) 
  ApiResponse<Profile2DTO> getProfile2(@SymbolCheck @RequestParam(required = true) String symbol)
    throws JsonProcessingException;
  
} 
