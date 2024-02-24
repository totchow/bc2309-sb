package com.vtxlab.bootcamp.bccryptocoingecko;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.impl.MarketController;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.request.CoinIdDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.service.MarketService;

@WebMvcTest(MarketController.class) 
public class MarketControllerTest {
  
  @MockBean
  MarketService marketService;

  @Autowired// WebMvcTest -> MockMvc() -> Spring context
  private MockMvc mockMvc;

  @Test
  void testgetcoins() throws Exception {
    List<CoinIdDTO> coinIdDTOs = new ArrayList<>();
    coinIdDTOs.add(new CoinIdDTO("bitcoin"));
    coinIdDTOs.add(new CoinIdDTO("ethereum"));

    Coin btc = Coin.builder()
                .id("bitcoin") //
                .symbol("btc") //
                .name("Bitcoin") //
                .image("https://...") //
                .current_price(50123) //
                .market_Cap(987654321000l) //
                .market_Cap_Rank(1) //
                .fully_diluted_valuation(1234567890123l) //
                .total_volume(22081148079l) //
                .high_24h(51234) //
                .low_24h(50000) //
                .price_change_24h(123) //
                .price_change_percentage_24h(0.123) //
                .market_cap_change_24h(3456789012l) //
                .market_cap_change_percentage_24h(0.234) //
                .circulating_supply(19636093) //
                .total_supply(21000000) //
                .max_supply(21000000) //
                .ath(69045) //
                .ath_change_percentage(-26.44175)
                .ath_date("2021-11-10T14:24:11.849Z")
                .atl(67.81)
                .atl_change_percentage(74798.79737)
                .atl_date("2013-07-06T00:00:00.000Z")
                .roi(null)
                .last_updated("2024-02-24T03:03:53.534Z")
                .build();
    List<Coin> coins = new ArrayList<>();
    coins.add(btc);

    Mockito.when(marketService.getCoins("usd", coinIdDTOs)).thenReturn(coins);

    mockMvc.perform(get("/crypto/coingecko/api/v1/coins?currency=usd"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data").value(""))
    .andDo(print());

    // mockMvc.perform(get("/crypto/coingecko/api/v1/coins")
    // .param("currency", "usd")
    // .param("ids",""))
  }
 
}

