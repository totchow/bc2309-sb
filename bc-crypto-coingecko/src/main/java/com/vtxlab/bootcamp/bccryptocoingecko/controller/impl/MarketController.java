package com.vtxlab.bootcamp.bccryptocoingecko.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.MarketOperation;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.RoiDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.MarketService;

@RestController
@RequestMapping(value = "crypto/coingecko/api/v1")
public class MarketController implements MarketOperation {
  
  @Autowired
  MarketService marketService;

  @Override
  public ApiResponse<List<CoinDTO>> getCoins(String currency, List<String> coinIds) 
  throws JsonProcessingException{
    
    List<Coin> coins = marketService.getCoins(currency,coinIds);

    List<CoinDTO> coindtos = coins.stream()
            .map(e -> { return CoinDTO.builder() //
                  .id(e.getId())
                  .symbol(e.getSymbol())
                  .name(e.getName())
                  .image(e.getImage())
                  .current_price(e.getCurrent_price())
                  .market_cap(e.getMarket_Cap())
                  .market_cap_rank(e.getMarket_Cap_Rank())
                  .fully_diluted_valuation(e.getFully_diluted_valuation())
                  .total_volume(e.getTotal_volume())
                  .high_24h(e.getHigh_24h())
                  .low_24h(e.getLow_24h())
                  .price_change_24h(e.getPrice_change_24h())
                  .price_change_percentage_24h(e.getPrice_change_percentage_24h())
                  .market_cap_change_24h(e.getMarket_cap_change_24h())
                  .market_cap_change_percentage_24h(e.getMarket_cap_change_percentage_24h())
                  .circulating_supply(e.getCirculating_supply())
                  .total_supply(e.getTotal_supply())
                  .max_supply(e.getMax_supply())
                  .ath(e.getAth())
                  .ath_change_percentage(e.getAth_change_percentage())
                  .ath_date(e.getAth_date())
                  .atl(e.getAtl())
                  .atl_change_percentage(e.getAtl_change_percentage())
                  .atl_date(e.getAth_date())
                  .roi(new RoiDTO(e.getRoi()))
                  .last_updated(e.getLast_updated())
                  .build();}
                ).collect(Collectors.toList());

    return ApiResponse.<List<CoinDTO>>builder().status(Syscode.OK).data(coindtos).build();

  }

}
