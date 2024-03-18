package com.vtxlab.bootcamp.bcproductdata.entity.stocks;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Class mapped to the db table
@Table(name = "tproduct_stocks_daily")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ProductStocksDailyEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "stock_id") // Foreign key
  @JsonBackReference
  private ProductStockListEntity stockListEntityDaily;

  @Column(name = "trade_date")
  private LocalDate tradeDate;
  @Column(name = "day_high")
  private double dayHigh;
  @Column(name = "day_low")
  private double dayLow;
  @Column(name = "day_open")
  private double dayOpen;
  @Column(name = "day_close")
  private double dayClose;

  public ProductStocksDailyEntity(long id, LocalDate tradeDate
    , double dayHigh, double dayLow, double dayOpen, double dayClose, long stockId) {

      this.id = id;
      this.tradeDate = tradeDate;
      this.dayHigh = dayHigh;
      this.dayLow = dayLow;
      this.dayOpen = dayOpen;
      this.dayClose = dayClose;
      this.stockListEntityDaily.setId(stockId);
  }

  public long getStockId() {
    return this.stockListEntityDaily.getId();
  }
}
