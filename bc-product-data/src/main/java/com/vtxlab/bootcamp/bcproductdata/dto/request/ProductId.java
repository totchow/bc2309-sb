package com.vtxlab.bootcamp.bcproductdata.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductId {
  
  private List<String> coins;
  private List<String> stocks;

}
