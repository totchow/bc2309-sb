package com.vtxlab.bootcamp.bcproductdata.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class StockDailyDTO {
  
  private String productId;
  private String tradeDate;
  private double dayHigh;
  private double dayLow;
  private double dayOpen;
  private double dayClose;
}
