package com.vtxlab.bootcamp.bcproductdata.repository.coins;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;

@Repository
public interface ProductCoinsRepository extends JpaRepository<ProductCoinsEntity, Long>{
  
  @Modifying
  @Transactional
  @Query(value = "update tproduct_coins t set curr_price = :cp, price_chg_pct = :pct, market_cap = :mc where t.coin_id = :id", nativeQuery = true)
  void updateCoinsData(@Param("cp") double cp, @Param("pct") double pct, @Param("mc") double mc, @Param("id") long id);

  @Query(value = "select t.* from tproduct_coins t where t.coin_id = :id", nativeQuery = true)
  ProductCoinsEntity findByCoinId(@Param("id") long id);

  // @Query(value = "select * from tproduct_coins", nativeQuery = true)
  // List<ProductCoinsEntity> findAlld();
}
