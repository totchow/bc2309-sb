package com.vtxlab.bootcamp.bcproductdata.dto.response;

import com.vtxlab.bootcamp.bcproductdata.model.dto.CoinDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO implements Comparable<ProductDTO>{
  
  private String productId;
  private String name;
  private double currentPrice;
  private double priceChangePct;
  private double marketCap;
  private String logo;

  @Override
  public int compareTo(ProductDTO productDTO) { // higher price come first
    if (productDTO.getMarketCap() > this.marketCap)
      return 1;
    return -1;
  }
}
