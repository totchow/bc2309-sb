package com.vtxlab.bootcamp.bcproductdata.repository.stocks;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;

@Repository
public interface ProductStocksDailyRepository extends JpaRepository<ProductStocksDailyEntity, Long> {
  
  @Query(value = "select t.* from tproduct_stocks_daily t where t.stock_id = :id", nativeQuery = true)
  List<ProductStocksDailyEntity> findByStockId(@Param("id") long id);
}
