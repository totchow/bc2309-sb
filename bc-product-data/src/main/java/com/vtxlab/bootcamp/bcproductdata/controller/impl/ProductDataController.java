package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.controller.ProductDataOperation;
import com.vtxlab.bootcamp.bcproductdata.dto.request.ProductId;
import com.vtxlab.bootcamp.bcproductdata.dto.response.ProductDTO;
import com.vtxlab.bootcamp.bcproductdata.dto.response.StockDailyDTO;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;
import com.vtxlab.bootcamp.bcproductdata.infra.ApiResponse;
import com.vtxlab.bootcamp.bcproductdata.infra.RedisHelper;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;
import com.vtxlab.bootcamp.bcproductdata.service.ProductdataService;

@RestController
@RequestMapping(value = "/data/api/v1")
public class ProductDataController implements ProductDataOperation{
  
  @Autowired
  ProductdataService productdataservice;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public ApiResponse<ProductId> addProduct(ProductId productIds) {

    ProductId newadd = productdataservice.addProduct(productIds);

    return ApiResponse.<ProductId>builder().status(Syscode.OK)
          .data(newadd)
          .build();
  }

  @Override
  public ApiResponse<List<String>> deleteProduct(ProductId productIds) {

    List<String> deleted = productdataservice.deleteProduct(productIds);

    return ApiResponse.<List<String>>builder().status(Syscode.OK)
          .data(deleted)
          .build();
  }

  @Override
  public ApiResponse<List<ProductDTO>> getProductCoins() {

    List<ProductCoinsEntity> pcEntity = productdataservice.getProductCoins();

    if (pcEntity.isEmpty())
      throw new RestClientException("RestClientException - Product Data Service is unavailable");

    List<ProductDTO> pcDTO = pcEntity.stream() //
      .map(e -> {
        return ProductDTO.builder() //
          .productId(e.getProductCoinListEntity().getCoinCode())
          .name(e.getName())
          .currentPrice(e.getCurrentPrice())
          .priceChangePct(e.getPriceChangePercentage24h())
          .marketCap(e.getMarketCap())
          .logo(e.getLogo())
          .build();
      }).collect(Collectors.toList());

    Collections.sort(pcDTO);
    return ApiResponse.<List<ProductDTO>>builder().status(Syscode.OK).data(pcDTO).build();
  }

  @Override
  public ApiResponse<List<ProductDTO>> getProductStocks() {

    List<ProductStocksEntity> psEntity = productdataservice.getProductStocks();

    if (psEntity.isEmpty())
      throw new RestClientException("RestClientException - Product Data Service is unavailable");

    List<ProductDTO> psDTO = psEntity.stream() //
      .map(e -> {
        return ProductDTO.builder()
        .productId(e.getStockListEntity().getStockCode())
        .name(e.getName())
        .currentPrice(e.getCurrentPrice())
        .priceChangePct(e.getPriceChgPct())
        .marketCap(e.getMarketCap())
        .logo(e.getLogo())
        .build();
      }).collect(Collectors.toList());

    Collections.sort(psDTO);

    return ApiResponse.<List<ProductDTO>>builder().status(Syscode.OK).data(psDTO).build();
  }

  @Override
  public ApiResponse<List<ProductDTO>> getProduct() {
    List<ProductStocksEntity> psEntity = productdataservice.getProductStocks();
    List<ProductCoinsEntity> pcEntity = productdataservice.getProductCoins();

    List<ProductDTO> coinList = pcEntity.stream() //
      .map(e -> {
        return ProductDTO.builder() //
          .productId(e.getProductCoinListEntity().getCoinCode())
          .name(e.getName())
          .currentPrice(e.getCurrentPrice())
          .priceChangePct(e.getPriceChangePercentage24h())
          .marketCap(e.getMarketCap())
          .logo(e.getLogo())
          .build();
      }).collect(Collectors.toList());

    List<ProductDTO> stockList = psEntity.stream() //
      .map(e -> {
        return ProductDTO.builder()
        .productId(e.getStockListEntity().getStockCode())
        .name(e.getName())
        .currentPrice(e.getCurrentPrice())
        .priceChangePct(e.getPriceChgPct())
        .marketCap(e.getMarketCap())
        .logo(e.getLogo())
        .build();
      }).collect(Collectors.toList());

    List<ProductDTO> fullList = new ArrayList<>();
    fullList.addAll(coinList);
    fullList.addAll(stockList);
    Collections.sort(fullList);
    return ApiResponse.<List<ProductDTO>>builder().status(Syscode.OK).data(fullList).build();

  }

  @Override
   public ApiResponse<List<StockDailyDTO>> getStockDaily(String code) throws JsonProcessingException{
    
    List<StockDailyDTO> dailyDTOs = redisHelper.getList("stock:daily:"+ code, StockDailyDTO.class);
    
    if (dailyDTOs != null) {
      return ApiResponse.<List<StockDailyDTO>>builder().status(Syscode.OK).data(dailyDTOs).build();
    } else {
      List<ProductStocksDailyEntity> dailyEntity = productdataservice.getStockDaily(code);

      dailyDTOs = dailyEntity.stream()
        .map(e -> {
          return StockDailyDTO.builder() //
            .productId(e.getStockListEntityDaily().getStockCode())
            .tradeDate(String.valueOf(e.getTradeDate()))
            .dayHigh(e.getDayHigh())
            .dayLow(e.getDayLow())
            .dayOpen(e.getDayOpen())
            .dayClose(e.getDayClose())
            .build();
        }).collect(Collectors.toList());

      redisHelper.set("stock:daily:"+ code, dailyDTOs, 60); // 60s test
    }

    return ApiResponse.<List<StockDailyDTO>>builder().status(Syscode.OK).data(dailyDTOs).build();

   }

}
