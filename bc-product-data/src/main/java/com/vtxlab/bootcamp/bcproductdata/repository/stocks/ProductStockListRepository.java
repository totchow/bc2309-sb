package com.vtxlab.bootcamp.bcproductdata.repository.stocks;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStockListEntity;

@Repository
public interface ProductStockListRepository extends JpaRepository<ProductStockListEntity, Long>{
  
  @Query(value = "select p.stockCode from ProductStockListEntity p")
  List<String> findAllStockCode();

  @Query(value = "select * from tproduct_stock_list where stock_code = :code", nativeQuery = true)
  ProductStockListEntity findByStockCode(@Param("code") String stockCode);

  @Modifying
  @Transactional
  @Query(value = "delete from ProductStockListEntity p where p.stockCode = :code")
  void deleteByStockCode(@Param("code") String code);
}
