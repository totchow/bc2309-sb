package com.vtxlab.bootcamp.bcproductdata.repository.stocks;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ExternalStockProfile2Entity;
import jakarta.transaction.Transactional;

@Repository
public interface ExternalStockProfile2Repository extends JpaRepository<ExternalStockProfile2Entity, Long> {
  
  @Query(value = 
  "select distinct t.* from texternal_stock_finnhub_profile2 t where t.quote_date = (select MAX(quote_date) from texternal_stock_finnhub_profile2 t2 where t2.quote_stock_code = t.quote_stock_code)", nativeQuery = true)
  List<ExternalStockProfile2Entity> findLatestStockProfile2();

  @Modifying
  @Transactional
  @Query(value = 
  "delete from texternal_stock_finnhub_profile2 where DATE_PART('day',localtimestamp - quote_date)* 24 + DATE_PART('hour', localtimestamp - quote_date) > 24", nativeQuery = true)
  void deleteDataby24HR();
}
