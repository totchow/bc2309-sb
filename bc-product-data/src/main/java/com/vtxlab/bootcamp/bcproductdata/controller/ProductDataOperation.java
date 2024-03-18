package com.vtxlab.bootcamp.bcproductdata.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.request.ProductId;
import com.vtxlab.bootcamp.bcproductdata.dto.response.ProductDTO;
import com.vtxlab.bootcamp.bcproductdata.dto.response.StockDailyDTO;
import com.vtxlab.bootcamp.bcproductdata.infra.ApiResponse;

public interface ProductDataOperation {
  
  @PostMapping(value = "/addproduct")
  @ResponseStatus(value = HttpStatus.OK) 
  ApiResponse<ProductId> addProduct(@RequestBody ProductId productIds);

  @DeleteMapping(value = "/deleteproduct")
  @ResponseStatus(value = HttpStatus.OK) 
  ApiResponse<List<String>> deleteProduct(@RequestBody ProductId productIds);

  @GetMapping(value = "/product/coins")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<ProductDTO>> getProductCoins();

  @GetMapping(value = "/product/stocks")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<ProductDTO>> getProductStocks();

  @GetMapping(value = "/product")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<ProductDTO>> getProduct();

  @GetMapping(value = "/product/stock/daily")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<StockDailyDTO>> getStockDaily(@RequestParam String code) throws JsonProcessingException;
}
