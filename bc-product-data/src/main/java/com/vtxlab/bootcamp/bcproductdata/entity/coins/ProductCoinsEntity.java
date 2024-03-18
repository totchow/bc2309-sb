package com.vtxlab.bootcamp.bcproductdata.entity.coins;

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
@Table(name = "tproduct_coins")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ProductCoinsEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "coin_id") // Foreign key
  @JsonBackReference
  private ProductCoinListEntity productCoinListEntity;

  private String name;
  @Column(name = "curr_price")
  private double currentPrice;
  @Column(name = "price_chg_pct")
  private double priceChangePercentage24h;
  @Column(name = "market_cap")
  private double marketCap;
  @Column(name = "logo")
  private String logo;


  public long getCoinId() {
    return this.productCoinListEntity.getId();
  }
}
