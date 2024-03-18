package com.vtxlab.bootcamp.bccryptocoingecko.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CoinDTO implements Comparable<CoinDTO>{
  
  private String id;
  private String symbol;
  private String name;
  private String image;
  @JsonProperty(value = "currentPrice")
  private double current_price;
  @JsonProperty(value = "marketCap")
  private long market_cap;
  @JsonProperty(value = "marketCapRank")
  private int market_cap_rank;
  @JsonProperty(value = "fullyDilutedValuation")
  private long fully_diluted_valuation;
  @JsonProperty(value = "totalVolume")
  private long total_volume;
  @JsonProperty(value = "high24h")
  private double high_24h;
  @JsonProperty(value = "low24h")
  private double low_24h;
  @JsonProperty(value = "priceChange24h")
  private double price_change_24h;
  @JsonProperty(value = "priceChangePercentage24h")
  private double price_change_percentage_24h;
  @JsonProperty(value = "marketCapChange24h")
  private long market_cap_change_24h;
  @JsonProperty(value = "marketCapChangePercentage24h")
  private double market_cap_change_percentage_24h;
  @JsonProperty(value = "circulatingSupply")
  private double circulating_supply;
  @JsonProperty(value = "totalSupply")
  private double total_supply;
  @JsonProperty(value = "maxSupply")
  private double max_supply;
  @JsonProperty(value = "ath")
  private double ath;
  @JsonProperty(value = "athChangePercentage")
  private double ath_change_percentage;
  @JsonProperty(value = "athDate")
  private String ath_date;
  @JsonProperty(value = "atl")
  private double atl;
  @JsonProperty(value = "atlChangePercentage")
  private double atl_change_percentage;
  @JsonProperty(value = "atlDate")
  private String atl_date;
  @JsonProperty(value = "roi")
  private RoiDTO roi;
  @JsonProperty(value = "lastUpdated")
  private String last_updated;

  @Override
  public int compareTo(CoinDTO coindto) { // higher price come first
    if (coindto.getMarket_cap_rank() < this.market_cap_rank)
      return 1;
    return -1;
  }

}
