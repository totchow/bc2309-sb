package com.vtxlab.bootcamp.bccryptocoingecko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Coin implements Comparable<Coin>{
  
  private String id;
  private String symbol;
  private String name;
  private String image;
  private double current_price;
  private long market_cap;
  private int market_cap_rank;
  private long fully_diluted_valuation;
  private long total_volume;
  private double high_24h;
  private double low_24h;
  private double price_change_24h;
  private double price_change_percentage_24h;
  private long market_cap_change_24h;
  private double market_cap_change_percentage_24h;
  private double circulating_supply;
  private double total_supply;
  private double max_supply;
  private double ath;
  private double ath_change_percentage;
  private String ath_date;
  private double atl;
  private double atl_change_percentage;
  private String atl_date;
  private Roi roi;
  private String last_updated;

  @Override
  public int compareTo(Coin coin) { // higher price come first
    if (coin.getMarket_cap_rank() < this.market_cap_rank)
      return 1;
    return -1;
  }
  // @JsonProperty(value = "current_price")
  // public void setCurrentPrice(double cprice) {
  //   this.current_price = cprice;
  // }

  // @JsonProperty(value = "market_cap")
  // public void setMarketCap(long cap) {
  //   this.market_Cap = cap;
  // }

  // @JsonProperty(value = "market_cap_rank")
  // public void setMarketCapRank (long rank) {
  //   this.market_Cap_Rank = rank;
  // }

  // @JsonProperty(value = "fully_diluted_valuation")
  // public void setfdv (long fdv) {
  //   this.fully_diluted_valuation = fdv;
  // }

  // @JsonProperty(value = "total_volume")
  // public void setTotalvolume (long volume) {
  //   this.total_volume = volume;
  // }

  // @JsonProperty(value = "high_24h")
  // public void setHigh24h (double h24h) {
  //   this.high_24h = h24h;
  // }

  // @JsonProperty(value = "low_24h")
  // public void setLow24h (long low24h) {
  //   this.low_24h = low24h;
  // }

  // @JsonProperty(value = "price_change_24h")
  // public void setPriceChange24h (double pc24h) {
  //   this.price_change_24h = pc24h;
  // }

  // @JsonProperty(value = "price_change_percentage_24h")
  // public void setPriceChangePer24h (double pcp24h) {
  //   this.price_change_percentage_24h = pcp24h;
  // }

  // @JsonProperty(value = "market_cap_change_24h")
  // public void setMarketCapChange24h(long mcc24h) {
  //   this.market_cap_change_24h = mcc24h;
  // }

  // @JsonProperty(value = "market_cap_change_percentage_24h")
  // public void setMarketCapChangePer24h (double mccp24h) {
  //   this.market_cap_change_percentage_24h = mccp24h;
  // }
  
  // @JsonProperty(value = "circulating_supply")
  // public void setCirculatingSupply (double cirSupply) {
  //   this.circulating_supply = cirSupply;
  // }

  // @JsonProperty(value = "total_supply")
  // public void setTotalSupply (double totalSupply) {
  //   this.total_supply= totalSupply;
  // }

  // @JsonProperty(value = "max_supply")
  // public void setMaxSupply (double maxSupply) {
  //   this.max_supply = maxSupply;
  // }

  // @JsonProperty(value = "ath_change_percentage")
  // public void setAthChangePer (double athCP) {
  //   this.ath_change_percentage = athCP;
  // }

  // @JsonProperty(value = "ath_date")
  // public void setAthDate (String athDate) {
  //   this.ath_date = athDate;
  // }

  // @JsonProperty(value = "atl_change_percentage")
  // public void setAtLChangePer (double atlCP) {
  //   this.atl_change_percentage = atlCP;
  // }

  // @JsonProperty(value = "atl_date")
  // public void setAtlDate (String atlDate) {
  //   this.atl_date = atlDate;
  // }

  // @JsonProperty(value = "last_updated")
  // public void setLastUpdated (String lastUpdated) {
  //   this.last_updated = lastUpdated;
  // }


}
