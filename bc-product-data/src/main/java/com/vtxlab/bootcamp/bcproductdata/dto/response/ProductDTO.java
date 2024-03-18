package com.vtxlab.bootcamp.bcproductdata.dto.response;

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
public class ProductDTO {
  
  private String productId;
  private String name;
  private double currentPrice;
  private double priceChangePct;
  private double marketCap;
  private String logo;
}
