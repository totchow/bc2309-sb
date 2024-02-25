package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class RedisHelper {
  //holder
  
  private RedisTemplate<String, String> redisTemplate;

  private ObjectMapper objectMapper;

  public RedisHelper(RedisConnectionFactory factory, 
      ObjectMapper objectMapper) {
    Objects.requireNonNull(factory);
    Objects.requireNonNull(objectMapper);

    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  // new RedisHelper().set("vincent", user);
  public void set(String key, Object value, int time) throws JsonProcessingException {
    //ObjectMapper objectMapper = this.objectMapper; // new ObjectMapper() in constructor;
    String serialized = this.objectMapper.writeValueAsString(value);
    this.redisTemplate.opsForValue().set(key, serialized, time, TimeUnit.SECONDS); // <String, String>
  }

  // User2 user = RedisHelper().get("vincent", User2.class);
  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException{
    String serialized = redisTemplate.opsForValue().get(key); // <String, String>

    if (serialized != null)
    return objectMapper.readValue(serialized, clazz);
    else return null;
  }

  public <T> List<T> getAll(Class<T> clazz) throws JsonProcessingException, JsonMappingException{
    List<T> all = new ArrayList<>();

    Set<String> keys = redisTemplate.keys("*");
    List<String> values = redisTemplate.opsForValue().multiGet(keys);

    for (String s: values) {
      all.add(objectMapper.readValue(s, clazz));
    }
    return all;
  }
}
