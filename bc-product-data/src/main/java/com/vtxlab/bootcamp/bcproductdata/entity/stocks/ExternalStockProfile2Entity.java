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

@Entity // Class mapped to the db table
@Table(name = "texternal_stock_finnhub_profile2")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ExternalStockProfile2Entity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "quote_date")
  private LocalDateTime quoteDate;
  @Column(name = "quote_stock_code")
  private String quoteStockCode;
  @Column(name = "country")
  private String country;
  @Column(name = "currency")
  private String currency;
  @Column(name = "estimateCurrency")
  private String estimateCurrency;
  @Column(name = "exchange")
  private String exchange;
  @Column(name = "finnhubIndustry")
  private String finnhubIndustry;
  @Column(name = "ipo")
  private String ipo;
  @Column(name = "logo")
  private String logo;
  @Column(name = "marketCapitalization")
  private double marketCapitalization;
  @Column(name = "name")
  private String name;
  @Column(name = "phone")
  private String phone;
  @Column(name = "shareOutstanding")
  private double shareOutstanding;
  @Column(name = "ticker")
  private String ticker;
  @Column(name = "weburl")
  private String weburl;

}
