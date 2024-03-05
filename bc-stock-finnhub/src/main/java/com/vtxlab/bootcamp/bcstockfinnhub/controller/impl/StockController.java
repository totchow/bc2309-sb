package com.vtxlab.bootcamp.bcstockfinnhub.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.controller.StockOperation;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.Profile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.QuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Profile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Quote;
import com.vtxlab.bootcamp.bcstockfinnhub.service.StockService;

@RestController
@RequestMapping(value = "/stock/finnhub/api/v1")
public class StockController implements StockOperation {
 
  @Autowired
  StockService stockService;

  @Override
  public ApiResponse<QuoteDTO> getQuote(String symbol) throws JsonProcessingException {

    Quote quote = stockService.getQuote(symbol);

    QuoteDTO quoteDto = QuoteDTO.builder()
                        .c(quote.getC())
                        .d(quote.getD())
                        .dp(quote.getDp())
                        .h(quote.getH())
                        .l(quote.getL())
                        .o(quote.getO())
                        .pc(quote.getPc())
                        .t(quote.getT())
                        .build();

      return ApiResponse.<QuoteDTO>builder().status(Syscode.OK).data(quoteDto).build();

  }

  @Override
  public ApiResponse<Profile2DTO> getProfile2(String symbol) throws JsonProcessingException {

    Profile2 profile2 = stockService.getProfile2(symbol);

    Profile2DTO profile2Dto = Profile2DTO.builder()
                              .country(profile2.getCountry())
                              .currency(profile2.getCurrency())
                              .estimateCurrency(profile2.getEstimateCurrency())
                              .exchange(profile2.getExchange())
                              .finnhubIndustry(profile2.getFinnhubIndustry())
                              .ipo(profile2.getIpo())
                              .logo(profile2.getLogo())
                              .marketCapitalization(profile2.getMarketCapitalization())
                              .name(profile2.getName())
                              .phone(profile2.getPhone())
                              .shareOutstanding(profile2.getShareOutstanding())
                              .ticker(profile2.getTicker())
                              .weburl(profile2.getWeburl())
                              .build();

      return ApiResponse.<Profile2DTO>builder().status(Syscode.OK).data(profile2Dto).build();

                              
  }

}
