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
import com.vtxlab.bootcamp.bccryptocoingecko.config.AppStartRunner;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.impl.MarketController;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coin;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Roi;
import com.vtxlab.bootcamp.bccryptocoingecko.service.MarketService;

@WebMvcTest(MarketController.class) 
public class MarketControllerTest {
  
  @MockBean
  MarketService marketService;

  @MockBean
  AppStartRunner appStartRunner;

  @Autowired// WebMvcTest -> MockMvc() -> Spring context
  private MockMvc mockMvc;

  @Test
  void testgetcoins() throws Exception {
    List<String> ids = new ArrayList<>();
    ids.add("bitcoin");
    //coinIdDTOs.add(new CoinIdDTO("ethereum"));

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

    Coin eth = Coin.builder()
                .id("ethereum") //
                .symbol("eth") //
                .name("Ethereum") //
                .image("https://...") //
                .current_price(2992.78) //
                .market_Cap(360242205907l) //
                .market_Cap_Rank(2) //
                .fully_diluted_valuation(360242205907l) //
                .total_volume(10965276172l) //
                .high_24h(2999.94) //
                .low_24h(2906.0) //
                .price_change_24h(49.55) //
                .price_change_percentage_24h(1.683) //
                .market_cap_change_24h(6498198335l) //
                .market_cap_change_percentage_24h(1.839) //
                .circulating_supply(120156541.646231) //
                .total_supply(120156541.646231) //
                .max_supply(0d) //
                .ath(4878.26) //
                .ath_change_percentage(-38.65815)
                .ath_date("2021-11-10T14:24:19.604Z")
                .atl(0.432979)
                .atl_change_percentage(691022.71551)
                .atl_date("2015-10-20T00:00:00.000Z")
                .roi(new Roi(76.623, "btc", 7662.3675))
                .last_updated("2024-02-24T18:01:50.992Z")
                .build();
    List<Coin> coins = new ArrayList<>();
    coins.add(btc);
    coins.add(eth);

    // mock appStartRunner to simulate server start to get coinIdlist to pass validator
    Mockito.when(appStartRunner.getCoinIds()).thenReturn(List.of("bitcoin","ethereum")); 

    Mockito.when(marketService.getCoins("usd", ids)).thenReturn(coins);

    mockMvc.perform(get("/crypto/coingecko/api/v1/coins?currency=usd&ids=bitcoin"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data[0].id").value("bitcoin"))
    .andExpect(jsonPath("$.data[0].symbol").value("btc"))
    .andExpect(jsonPath("$.data[0].name").value("Bitcoin"))
    .andExpect(jsonPath("$.data[0].image").value("https://..."))
    .andExpect(jsonPath("$.data[0].currentPrice").value("50123.0"))
    .andExpect(jsonPath("$.data[0].marketCap").value("987654321000"))
    .andExpect(jsonPath("$.data[0].marketCapRank").value("1"))
    .andExpect(jsonPath("$.data[0].fullyDilutedValuation").value("1234567890123"))
    .andExpect(jsonPath("$.data[0].totalVolume").value("22081148079"))
    .andExpect(jsonPath("$.data[0].high24h").value("51234.0"))
    .andExpect(jsonPath("$.data[0].low24h").value("50000.0"))
    .andExpect(jsonPath("$.data[0].priceChange24h").value("123.0"))
    .andExpect(jsonPath("$.data[0].priceChangePercentage24h").value("0.123"))
    .andExpect(jsonPath("$.data[0].marketCapChange24h").value("3456789012"))
    .andExpect(jsonPath("$.data[0].marketCapChangePercentage24h").value("0.234"))
    .andExpect(jsonPath("$.data[0].circulatingSupply").value(String.valueOf(19636093d)))
    .andExpect(jsonPath("$.data[0].totalSupply").value(String.valueOf(21000000d)))
    .andExpect(jsonPath("$.data[0].maxSupply").value(String.valueOf(21000000d)))
    .andExpect(jsonPath("$.data[0].ath").value("69045.0"))
    .andExpect(jsonPath("$.data[0].athChangePercentage").value("-26.44175"))
    .andExpect(jsonPath("$.data[0].athDate").value("2021-11-10T14:24:11.849Z"))
    .andExpect(jsonPath("$.data[0].atl").value("67.81"))
    .andExpect(jsonPath("$.data[0].atlChangePercentage").value("74798.79737"))
    .andExpect(jsonPath("$.data[0].atlDate").value("2013-07-06T00:00:00.000Z"))
    .andExpect(jsonPath("$.data[0].roi").isEmpty())
    .andExpect(jsonPath("$.data[0].lastUpdated").value("2024-02-24T03:03:53.534Z"))
    .andExpect(jsonPath("$.data[1].id").value("ethereum"))
    .andExpect(jsonPath("$.data[1].symbol").value("eth"))
    .andExpect(jsonPath("$.data[1].name").value("Ethereum"))
    .andExpect(jsonPath("$.data[1].image").value("https://..."))
    .andExpect(jsonPath("$.data[1].currentPrice").value("2992.78"))
    .andExpect(jsonPath("$.data[1].marketCap").value("360242205907"))
    .andExpect(jsonPath("$.data[1].marketCapRank").value("2"))
    .andExpect(jsonPath("$.data[1].fullyDilutedValuation").value("360242205907"))
    .andExpect(jsonPath("$.data[1].totalVolume").value("10965276172"))
    .andExpect(jsonPath("$.data[1].high24h").value("2999.94"))
    .andExpect(jsonPath("$.data[1].low24h").value("2906.0"))
    .andExpect(jsonPath("$.data[1].priceChange24h").value("49.55"))
    .andExpect(jsonPath("$.data[1].priceChangePercentage24h").value("1.683"))
    .andExpect(jsonPath("$.data[1].marketCapChange24h").value("6498198335"))
    .andExpect(jsonPath("$.data[1].marketCapChangePercentage24h").value("1.839"))
    .andExpect(jsonPath("$.data[1].circulatingSupply").value(String.valueOf(120156541.646231)))
    .andExpect(jsonPath("$.data[1].totalSupply").value(String.valueOf(120156541.646231)))
    .andExpect(jsonPath("$.data[1].maxSupply").value("0.0"))
    .andExpect(jsonPath("$.data[1].ath").value("4878.26"))
    .andExpect(jsonPath("$.data[1].athChangePercentage").value("-38.65815"))
    .andExpect(jsonPath("$.data[1].athDate").value("2021-11-10T14:24:19.604Z"))
    .andExpect(jsonPath("$.data[1].atl").value("0.432979"))
    .andExpect(jsonPath("$.data[1].atlChangePercentage").value("691022.71551"))
    .andExpect(jsonPath("$.data[1].atlDate").value("2015-10-20T00:00:00.000Z"))
    .andExpect(jsonPath("$.data[1].roi.times").value(76.623))
    .andExpect(jsonPath("$.data[1].roi.currency").value("btc"))
    .andExpect(jsonPath("$.data[1].roi.percentage").value(7662.3675))
    .andExpect(jsonPath("$.data[1].lastUpdated").value("2024-02-24T18:01:50.992Z"))
    .andDo(print());


  }
 
}

