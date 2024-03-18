package com.vtxlab.bootcamp.bcproductdata.entity.stocks;

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

@Entity
@Table(name = "texternal_stock_finnhub_quote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExternalStockQuoteEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "quote_date")
  private LocalDateTime quoteDate;
  @Column(name = "quote_stock_code")
  private String quoteStockCode;
  @Column(name = "curr_price")
  private double currentPrice;
  @Column(name = "price_chg")
  private double priceChg;
  @Column(name = "price_chg_pct")
  private double priceChgPct;
  @Column(name = "price_day_high")
  private double priceDayHigh; 
  @Column(name = "price_day_low")
  private double priceDayLow;  
  @Column(name = "price_prev_open")
  private double pricePrevOpen; 
  @Column(name = "price_prev_close")
  private double pricePrevClose;  
}
