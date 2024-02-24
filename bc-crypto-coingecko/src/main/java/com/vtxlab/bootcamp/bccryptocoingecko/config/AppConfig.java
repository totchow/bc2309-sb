package com.vtxlab.bootcamp.bccryptocoingecko.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.RedisHelper;


@Configuration
public class AppConfig {
  
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  RedisHelper redisHelper(RedisConnectionFactory factory // auto create in context
    , ObjectMapper objectMapper) {
      return new RedisHelper(factory, objectMapper);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }


}
