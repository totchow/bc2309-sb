package com.vtxlab.bootcamp.bcproductdata.entity.stocks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Class mapped to the db table
@Table(name = "tproduct_stocks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ProductStocksEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "stock_id") // Foreign key
  @JsonBackReference
  private ProductStockListEntity stockListEntity;

  private String name;
  @Column(name = "curr_price")
  private double currentPrice;
  @Column(name = "price_chg_pct")
  private double priceChgPct;
  @Column(name = "market_cap")
  private double marketCap;
  private String logo;

  public long getStockId() {
    return this.stockListEntity.getId();
  }

  
}
