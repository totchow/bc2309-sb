package com.vtxlab.bootcamp.bcproductdata.repository.coins;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ExternalCoinsEntity;
import jakarta.transaction.Transactional;

@Repository
public interface ExternalCoinsRepository extends JpaRepository<ExternalCoinsEntity, Long> {
  
  @Query(value = 
    "select distinct t.* from texternal_crypto_coingecko_market t where t.quote_date = (select MAX(quote_date) from texternal_crypto_coingecko_market t2 where t2.quote_coin_code = t.quote_coin_code)", nativeQuery = true)
  List<ExternalCoinsEntity> findLatestCoinsData();

  @Modifying
  @Transactional
  @Query(value = 
   "delete from texternal_crypto_coingecko_market where DATE_PART('day',localtimestamp - quote_date)* 24 + DATE_PART('hour', localtimestamp - quote_date) > 24", nativeQuery = true)
   void deleteDataby24HR();

}
