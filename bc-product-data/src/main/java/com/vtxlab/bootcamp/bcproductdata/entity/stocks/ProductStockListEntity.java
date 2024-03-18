package com.vtxlab.bootcamp.bcproductdata.entity.stocks;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Class mapped to the db table
@Table(name = "tproduct_stock_list")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ProductStockListEntity {
  
  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "stock_code", unique = true)
  private String stockCode;

  @OneToOne(mappedBy = "stockListEntity", cascade = CascadeType.MERGE, orphanRemoval = true)
  @JsonManagedReference
  private ProductStocksEntity productStocksEntity;

  @OneToMany(mappedBy = "stockListEntityDaily", fetch=FetchType.EAGER, orphanRemoval = true)
  @JsonManagedReference
  private List<ProductStocksDailyEntity> productStocksDailyEntity = new ArrayList<>();

  public void updateStocksDailyEntity(ProductStocksDailyEntity p) {
    this.productStocksDailyEntity.add(p);
  }
}
