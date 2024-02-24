package com.vtxlab.bootcamp.bccryptocoingecko.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.request.CoinIdDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.RedisHelper;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.MarketService;

@Service
public class MarketServiceHolder implements MarketService {
  
  private static final String key = "&x_cg_demo_api_key=CG-WMdqgRJLLNwTQStejmEWZjSz";

  @Value("${api.coingecko.domain}")
  private String domain;

  @Value("${api.coingecko.endpoints.coins}")
  private String uri;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public List<Coin> getCoins(String currency, List<CoinIdDTO> coinIdDTOs) 
    throws JsonProcessingException{

    String ids = CoinIdDTO.uriString(coinIdDTOs); //form uri ids string e.g. "&ids=bitcoin,ehtereum"
    
    String coingeckoUri = domain + uri +
            "?vs_currency="+currency
            +ids
            +key;
      
    try { // get data from coingecko
      Coin[] coins = restTemplate.getForObject(coingeckoUri, Coin[].class);
      
      for (Coin c : coins) { // save to Redis, 60s expiry
        redisHelper.set("crypto:coingecko:coins-markets:"+ currency + ":" + c.getId()
           , c, 60);
      }

      return Arrays.asList(coins);
    } catch (RestClientException e) { // try get from Redis if cannot get from coingecko

        List<Coin> coins = new ArrayList<>();

        if (coinIdDTOs == null) { // if no ids input , get all from redis  
          coins =  redisHelper.getAll(Coin.class);
        } else {

            for (CoinIdDTO c : coinIdDTOs) { // get each id input from redis
              coins.add(redisHelper.get("crypto:coingecko:coins-markets:"+ currency + ":" 
                  + c.getCoinId(), Coin.class));          
            }
        }
      
        if (coins.isEmpty()) { // if no data in redis, throw
          throw new RestClientException("RestClientException - coingecko service is unavailable");
        } else {
          Collections.sort(coins);
          return coins;
        }
    }

  }

}
