package com.vtxlab.bootcamp.bccryptocoingecko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// all available coins
@AllArgsConstructor
@Getter
public class CoinId {

  private String id;
  private String symbol;
  private String name;


}
