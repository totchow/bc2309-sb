package com.vtxlab.bootcamp.bcproductdata;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.vtxlab.bootcamp.bcproductdata.controller.impl.ProductDataController;
import com.vtxlab.bootcamp.bcproductdata.dto.response.StockDailyDTO;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStockListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;
import com.vtxlab.bootcamp.bcproductdata.infra.RedisHelper;
import com.vtxlab.bootcamp.bcproductdata.service.ProductdataService;

@WebMvcTest(ProductDataController.class) 
public class ProductDataControllerTest {
  
  @MockBean
  ProductdataService productDataService;

  @MockBean
  RedisHelper redisHelper;

  @Autowired// WebMvcTest -> MockMvc() -> Spring context
  private MockMvc mockMvc;

  @Test
  void testgetProductCoins() throws Exception {
    ProductCoinsEntity bitcoin = ProductCoinsEntity.builder()
      .id(1)
      .productCoinListEntity(new ProductCoinListEntity())
      .name("Bitcoin")
      .currentPrice(66666)
      .priceChangePercentage24h(1.23456)
      .marketCap(98765432111l)
      .logo("https://btcbtc.btc")
      .build();
    bitcoin.getProductCoinListEntity().setCoinCode("bitcoin");

    List<ProductCoinsEntity> pcEntity = new ArrayList<>();
    pcEntity.add(bitcoin);

    ProductStocksEntity aapl = ProductStocksEntity.builder() // 
      .id(1)
      .stockListEntity(new ProductStockListEntity())
      .name("Apple inc")
      .currentPrice(199)
      .priceChgPct(3.3333)
      .marketCap(19999999999l)
      .logo("https://appleorange")
      .build();
    aapl.getStockListEntity().setStockCode("AAPL");
    List<ProductStocksEntity> psEntity = new ArrayList<>();
    psEntity.add(aapl);

    Mockito.when(productDataService.getProductCoins()).thenReturn(pcEntity);  
    Mockito.when(productDataService.getProductStocks()).thenReturn(psEntity);  

    mockMvc.perform(get("/data/api/v1/product/coins"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data[0].productId").value("bitcoin"))
    .andExpect(jsonPath("$.data[0].name").value("Bitcoin"))
    .andExpect(jsonPath("$.data[0].currentPrice").value(66666))
    .andExpect(jsonPath("$.data[0].priceChangePct").value(1.23456))
    .andExpect(jsonPath("$.data[0].marketCap").value(98765432111l))
    .andExpect(jsonPath("$.data[0].logo").value("https://btcbtc.btc"))
    .andDo(print());

    mockMvc.perform(get("/data/api/v1/product/stocks"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data[0].productId").value("AAPL"))
    .andExpect(jsonPath("$.data[0].name").value("Apple inc"))
    .andExpect(jsonPath("$.data[0].currentPrice").value(199))
    .andExpect(jsonPath("$.data[0].priceChangePct").value(3.3333))
    .andExpect(jsonPath("$.data[0].marketCap").value(19999999999l))
    .andExpect(jsonPath("$.data[0].logo").value("https://appleorange"))
    .andDo(print());

    mockMvc.perform(get("/data/api/v1/product"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data[0].productId").value("bitcoin"))
    .andExpect(jsonPath("$.data[0].name").value("Bitcoin"))
    .andExpect(jsonPath("$.data[0].currentPrice").value(66666))
    .andExpect(jsonPath("$.data[0].priceChangePct").value(1.23456))
    .andExpect(jsonPath("$.data[0].marketCap").value(98765432111l))
    .andExpect(jsonPath("$.data[0].logo").value("https://btcbtc.btc"))
    .andExpect(jsonPath("$.data[1].productId").value("AAPL"))
    .andExpect(jsonPath("$.data[1].name").value("Apple inc"))
    .andExpect(jsonPath("$.data[1].currentPrice").value(199))
    .andExpect(jsonPath("$.data[1].priceChangePct").value(3.3333))
    .andExpect(jsonPath("$.data[1].marketCap").value(19999999999l))
    .andExpect(jsonPath("$.data[1].logo").value("https://appleorange"))
    .andDo(print());
  }

  @Test
  void testgetStockDaily() throws Exception {

    ProductStocksDailyEntity aaplday1 = ProductStocksDailyEntity.builder() //
      .id(1)
      .stockListEntityDaily(new ProductStockListEntity())
      .tradeDate(LocalDate.of(2024, 3, 1))
      .dayHigh(111.1)
      .dayLow(100.3)
      .dayOpen(105.5)
      .dayClose(122.2)
      .build();
    aaplday1.getStockListEntityDaily().setStockCode("AAPL");

    ProductStocksDailyEntity aaplday2 = ProductStocksDailyEntity.builder() //
    .id(2)
    .stockListEntityDaily(new ProductStockListEntity())
    .tradeDate(LocalDate.of(2024, 3, 2))
    .dayHigh(133.33)
    .dayLow(88.98)
    .dayOpen(122.2)
    .dayClose(100.2)
    .build();
    aaplday2.getStockListEntityDaily().setStockCode("AAPL");

    ProductStocksDailyEntity aaplday3 = ProductStocksDailyEntity.builder() //
    .id(3)
    .stockListEntityDaily(new ProductStockListEntity())
    .tradeDate(LocalDate.of(2024, 3, 3))
    .dayHigh(199.99)
    .dayLow(66.666)
    .dayOpen(100.2)
    .dayClose(100.2)
    .build();
    aaplday3.getStockListEntityDaily().setStockCode("AAPL");

    List<ProductStocksDailyEntity> psDaily = new ArrayList<>();
    psDaily.add(aaplday1);
    psDaily.add(aaplday2);
    psDaily.add(aaplday3);

    Mockito.when(productDataService.getStockDaily("AAPL")).thenReturn(psDaily);  
    Mockito.when(redisHelper.getList("stock:daily:"+ "AAPL", StockDailyDTO.class)).thenReturn(null);

    mockMvc.perform(get("/data/api/v1/product/stock/daily?code=AAPL"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data[0].productId").value("AAPL"))
    .andExpect(jsonPath("$.data[0].tradeDate").value("2024-03-01"))
    .andExpect(jsonPath("$.data[0].dayHigh").value(111.1))
    .andExpect(jsonPath("$.data[0].dayLow").value(100.3))
    .andExpect(jsonPath("$.data[0].dayOpen").value(105.5))
    .andExpect(jsonPath("$.data[0].dayClose").value(122.2))
    .andExpect(jsonPath("$.data[1].productId").value("AAPL"))
    .andExpect(jsonPath("$.data[1].tradeDate").value("2024-03-02"))
    .andExpect(jsonPath("$.data[1].dayHigh").value(133.33))
    .andExpect(jsonPath("$.data[1].dayLow").value(88.98))
    .andExpect(jsonPath("$.data[1].dayOpen").value(122.2))
    .andExpect(jsonPath("$.data[1].dayClose").value(100.2))
    .andExpect(jsonPath("$.data[2].productId").value("AAPL"))
    .andExpect(jsonPath("$.data[2].tradeDate").value("2024-03-03"))
    .andExpect(jsonPath("$.data[2].dayHigh").value(199.99))
    .andExpect(jsonPath("$.data[2].dayLow").value(66.666))
    .andExpect(jsonPath("$.data[2].dayOpen").value(100.2))
    .andExpect(jsonPath("$.data[2].dayClose").value(100.2))
    .andDo(print());

  }
}
