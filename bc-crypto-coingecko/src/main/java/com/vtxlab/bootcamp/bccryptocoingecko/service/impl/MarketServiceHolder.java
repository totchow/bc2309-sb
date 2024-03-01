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
  public List<Coin> getCoins(String currency, List<String> coinIds) 
    throws JsonProcessingException{

    String ids = "&ids=" + String.join(",",coinIds);

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

    } catch (RestClientException e) { // get from Redis if cannot get from coingecko

         List<Coin> coins = new ArrayList<>();

        if (coinIds.isEmpty()) { // if no ids specified , return all data from redis  
          coins = redisHelper.getAll(Coin.class);

        if (coins.isEmpty())  // if no result in redis, throw
          throw new RestClientException("RestClientException - coingecko service is unavailable");
  
        } else {
            for (String c : coinIds) { // get each id input from redis
              Coin c1 = redisHelper.get("crypto:coingecko:coins-markets:"+ currency + ":" 
              + c, Coin.class);

              if (c1 != null) { // key is found
                coins.add(c1);
              } else { // key not found in redis, throw
                throw new RestClientException("RestClientException - coingecko service is unavailable");
              }
            }
         }
      
        Collections.sort(coins);
        return coins;
    }
   }
}

