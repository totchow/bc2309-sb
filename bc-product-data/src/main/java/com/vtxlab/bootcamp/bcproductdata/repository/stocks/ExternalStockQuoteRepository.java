package com.vtxlab.bootcamp.bcproductdata.repository.stocks;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ExternalStockQuoteEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ExternalStockQuoteRepository extends JpaRepository<ExternalStockQuoteEntity, Long> {
  
  @Query(value = 
    "select distinct t.* from texternal_stock_finnhub_quote t where t.quote_date = (select MAX(quote_date) from texternal_stock_finnhub_quote t2 where t2.quote_stock_code = t.quote_stock_code)", nativeQuery = true)
  List<ExternalStockQuoteEntity> findLatestStockQuote();

  @Modifying
  @Transactional
  @Query(value = 
  "delete from texternal_stock_finnhub_quote where DATE_PART('day',localtimestamp - quote_date)* 24 + DATE_PART('hour', localtimestamp - quote_date) > 24", nativeQuery = true)
  void deleteDataby24HR();
}
