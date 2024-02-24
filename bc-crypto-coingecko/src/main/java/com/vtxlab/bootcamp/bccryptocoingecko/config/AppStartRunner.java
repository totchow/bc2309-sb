package com.vtxlab.bootcamp.bccryptocoingecko.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinId;

@Component
public class AppStartRunner implements CommandLineRunner{
  
  @Autowired
  RestTemplate restTemplate;

  private List<String> coinIds = new ArrayList<>();;

  @Override
  public void run(String ... args) {

  // get all valid coin-id from coingecko api coins/List when server start
  coinIds = Arrays.asList(restTemplate.getForObject("https://api.coingecko.com/api/v3/coins/list?include_platform=false",
        CoinId[].class)).stream().map(c -> c.getId()).toList();

  }

  public List<String> getCoinIds() {
    return this.coinIds;
  };
}
