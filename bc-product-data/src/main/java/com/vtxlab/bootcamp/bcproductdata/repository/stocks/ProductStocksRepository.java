package com.vtxlab.bootcamp.bcproductdata.repository.stocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;

@Repository
public interface ProductStocksRepository extends JpaRepository<ProductStocksEntity, Long> {
  
  @Modifying
  @Transactional
  @Query(value = "update tproduct_stocks t set curr_price = :cp, price_chg_pct = :pct, market_cap = :mc where t.stock_id = :id", nativeQuery = true)
  void updateStocksData(@Param("cp") double cp, @Param("pct") double pct, @Param("mc") double mc, @Param("id") long id);

  @Query(value = "select t.* from tproduct_stocks t where t.stock_id = :id", nativeQuery = true)
  ProductStocksEntity findByStockId(@Param("id") long id);
}
