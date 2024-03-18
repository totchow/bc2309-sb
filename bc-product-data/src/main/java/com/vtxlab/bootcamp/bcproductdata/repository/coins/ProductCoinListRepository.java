package com.vtxlab.bootcamp.bcproductdata.repository.coins;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinListEntity;

@Repository
public interface ProductCoinListRepository extends JpaRepository<ProductCoinListEntity, Long>  {
  
  @Modifying
  @Transactional
  @Query(value = "delete from ProductCoinListEntity p where p.coinCode = :code")
  void deleteBycoinCode(@Param("code") String code);

  @Query(value = "select p.coinCode from ProductCoinListEntity p")
  List<String> findAllCoinCode();

  @Query(value = "select * from tproduct_coin_list where coin_code = :code", nativeQuery = true)
  ProductCoinListEntity findByCoinCode(@Param("code") String coinCode);

}
