package com.vtxlab.bootcamp.bcproductdata.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.request.ProductId;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStockListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ProductCoinListRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ProductCoinsRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStockListRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStocksDailyRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStocksRepository;
import com.vtxlab.bootcamp.bcproductdata.service.ProductdataService;

@Service
@EnableScheduling
public class ProductDataServiceimpl implements ProductdataService {
  
  @Autowired
  private ProductCoinListRepository productCoinListRepository;

  @Autowired
  private ProductStockListRepository productStockListRepository;

  @Autowired
  private ProductCoinsRepository productCoinsRepository;

  @Autowired
  private ProductStocksRepository productStocksRepository;

  @Autowired
  private ProductStocksDailyRepository productStocksDailyRepository;

  @Override
  public ProductId addProduct(ProductId productIds) {

    if (productIds.getCoins() != null) {
      List<ProductCoinListEntity> coinsEntity 
          = productIds.getCoins().stream()
          .map(e -> {return ProductCoinListEntity.builder()
                .coinCode(e)
                .build();})
          .toList();

      for (ProductCoinListEntity c: coinsEntity) {
        productCoinListRepository.save(c);
      }
    }

    if (productIds.getStocks() != null) {
      List<ProductStockListEntity> stocksEntity 
          = productIds.getStocks().stream()
          .map(e -> {return ProductStockListEntity.builder()
                .stockCode(e)
                .build();})
          .toList();

      for (ProductStockListEntity s: stocksEntity) {
        productStockListRepository.save(s);
      }
    }
    return productIds;
  }

  @Override
  public List<String> deleteProduct(ProductId productIds) {

    if (productIds.getCoins() != null) {
      for (String c: productIds.getCoins()) {
        productCoinListRepository.deleteBycoinCode(c);
      }
    }

    if (productIds.getStocks() != null) {
      for (String c: productIds.getStocks()) {
        productStockListRepository.deleteByStockCode(c);;
      }
    }
    return null;
  }

  @Override
  public List<ProductCoinsEntity> getProductCoins() {
    return productCoinsRepository.findAll();
  }

  @Override
  public List<ProductStocksEntity> getProductStocks() {
    return productStocksRepository.findAll();
  }

  @Override
  public List<ProductStocksDailyEntity> getStockDaily(String code) throws JsonProcessingException {
    long stockId = productStockListRepository.findByStockCode(code).getId();
    return productStocksDailyRepository.findByStockId(stockId);
  }
  

  public static LocalDateTime convertDateTime(String datetime) {
    ZonedDateTime parse = 
      ZonedDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME)
        .withZoneSameInstant(ZoneId.systemDefault());
    return parse.toLocalDateTime();
  }
}
