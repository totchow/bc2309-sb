package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StockSymbol {
  TSLA("TSLA"),
  AAPL("AAPL"),
  MSFT("MSFT"),
  NVDA("NVDA");

  private String symbol;

  public static boolean checkStockSymbol(String symbol) {
    boolean result = switch (symbol.toUpperCase()) {
      case "TSLA", "AAPL", "MSFT", "NVDA" ->  true;
      default -> false;
    };
    return result;
  }
}
