package com.vtxlab.bootcamp.bcproductdata.entity.coins;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Class mapped to the db table
@Table(name = "texternal_crypto_coingecko_market")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ExternalCoinsEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "quote_date")
  private LocalDateTime quoteDate;
  @Column(name = "quote_coin_code")
  private String quoteCoinCode;
  @Column(name = "quote_currency")
  private String quoteCurrency;
  private String name;
  private String image;
  @Column(name = "curr_price")
  private double currentPrice;
  @Column(name = "market_cap")
  private double marketCap;
  @Column(name = "market_cap_rank")
  private int marketCapRank;
  @Column(name = "fully_diluted_valuation")
  private double fullyDilutedValuation;
  @Column(name = "total_volume")
  private double totalVolume;
  @Column(name = "high_24h")
  private double high24h;
  @Column(name = "low_24h")
  private double low24h;
  @Column(name = "price_change_24h")
  private double priceChange24h;
  @Column(name = "price_change_percentage_24h")
  private double priceChangePercentage24h;
  @Column(name = "market_cap_change_24h")
  private double marketCapChange24h;
  @Column(name = "market_cap_change_percentage_24h")
  private double marketCapChangePercentage24h;
  @Column(name = "circulating_supply")
  private double circulatingSupply;
  @Column(name = "total_supply")
  private double totalSupply;
  @Column(name = "max_supply")
  private double maxSupply;
  private double ath;
  @Column(name = "ath_change_percentage")
  private double athChangePercentage;
  @Column(name = "ath_date")
  private LocalDateTime athDate;
  private double atl;
  @Column(name = "atl_change_percentage")
  private double atlChangePercentage;
  @Column(name = "atl_date")
  private LocalDateTime atlDate;
}
