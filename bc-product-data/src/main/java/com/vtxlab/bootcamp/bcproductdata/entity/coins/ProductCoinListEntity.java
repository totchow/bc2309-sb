package com.vtxlab.bootcamp.bcproductdata.entity.coins;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Class mapped to the db table
@Table(name = "tproduct_coin_list")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ProductCoinListEntity {
  

  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "coin_code", unique = true)
  private String coinCode;

  @OneToOne(mappedBy = "productCoinListEntity", cascade = CascadeType.MERGE, orphanRemoval = true)
  @JsonManagedReference
  private ProductCoinsEntity productCoinsEntity;

}
