package com.vtxlab.bootcamp.bcproductdata.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.request.ProductId;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;

public interface ProductdataService {
  
  ProductId addProduct(ProductId productIds);

  List<String> deleteProduct(ProductId productIds);

  List<ProductCoinsEntity> getProductCoins();

  List<ProductStocksEntity> getProductStocks();

  List<ProductStocksDailyEntity> getStockDaily(String code) throws JsonProcessingException;

  long getStockId(String code);
}
