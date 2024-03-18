package com.vtxlab.bootcamp.bcproductdata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profile2DTO {
  
  private String country;
  private String currency;
  private String estimateCurrency;
  private String exchange;
  private String finnhubIndustry;
  private String ipo;
  private String logo;
  private double marketCapitalization;
  private String name;
  private String phone;
  private double shareOutstanding;
  private String ticker;
  private String weburl;
}
