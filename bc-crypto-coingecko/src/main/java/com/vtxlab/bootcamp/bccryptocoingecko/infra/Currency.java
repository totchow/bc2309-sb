package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
  usd("usd"),
  hkd("hkd"),
  eur("eur");

  private String currency;

  public static boolean checkCurrency(String currency) {
    boolean result = switch (currency.toLowerCase()) {
      case "usd", "hkd", "eur" ->  true;
      default -> false;
    };
    return result;
  }

}
