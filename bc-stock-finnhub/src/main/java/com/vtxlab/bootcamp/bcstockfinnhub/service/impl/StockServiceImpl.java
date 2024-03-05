package com.vtxlab.bootcamp.bcstockfinnhub.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.RedisHelper;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Profile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Quote;
import com.vtxlab.bootcamp.bcstockfinnhub.service.StockService;

@Service
public class StockServiceImpl implements StockService{

  private static final String token = "&token=cnhebj9r01qhlslit4t0cnhebj9r01qhlslit4tg";

  @Value("${api.finnhub.domain}")
  private String domain;

  @Value("${api.finnhub.endpoints.quote}")
  private String quoteUri;

  @Value("${api.finnhub.endpoints.profile2}")
  private String profile2Uri;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper redisHelper;


  @Override
  public Quote getQuote(String symbol) throws JsonProcessingException {

    String FinnhubQuoteUrl = domain 
                            + quoteUri 
                            + "?symbol=" + symbol
                            + token;

    try { // get data from Finnhub
      Quote quote = restTemplate.getForObject(FinnhubQuoteUrl, Quote.class);
      
      redisHelper.set("stock:finnhub:quote:" + symbol, quote, 60);

      return quote;

    } catch (RestClientException e) { // get from Redis if cannot get from coingecko

      Quote quote = redisHelper.get("stock:finnhub:quote:" + symbol, Quote.class);

        if (quote == null)  // if no result in redis, throw
          throw new RestClientException("RestClientException - Finnhub service is unavailable");
   
        return quote;
    }

  };

  @Override
  public Profile2 getProfile2(String symbol) throws JsonProcessingException {
    
    String FinnhubProfile2Url = domain 
                            + profile2Uri 
                            + "?symbol=" + symbol
                            + token;

    try { // get data from Finnhub
      Profile2 profile2 = restTemplate.getForObject(FinnhubProfile2Url, Profile2.class);
      
      redisHelper.set("stock:finnhub:profile2:" + symbol, profile2, 60);

      return profile2;

    } catch (RestClientException e) { // get from Redis if cannot get from coingecko

      Profile2 profile2 = redisHelper.get("stock:finnhub:profile2:" + symbol, Profile2.class);

        if (profile2 == null)  // if no result in redis, throw
          throw new RestClientException("RestClientException - Finnhub service is unavailable");
   
        return profile2;
    }

  };
}
