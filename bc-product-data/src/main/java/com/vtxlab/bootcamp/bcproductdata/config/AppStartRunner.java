package com.vtxlab.bootcamp.bcproductdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ProductCoinListRepository;

@Component
public class AppStartRunner implements CommandLineRunner {
  
  @Autowired
  private ProductCoinListRepository productCoinListRepository;

  @Override
  public void run(String ... args) throws Exception {

    // if (productCoinListRepository.findAllCoinCode().size() == 0) {
    //   throw new Exception();
    // }

  }
}
