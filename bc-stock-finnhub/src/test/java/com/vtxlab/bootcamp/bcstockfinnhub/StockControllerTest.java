package com.vtxlab.bootcamp.bcstockfinnhub;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.vtxlab.bootcamp.bcstockfinnhub.controller.impl.StockController;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Profile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.Quote;
import com.vtxlab.bootcamp.bcstockfinnhub.service.StockService;

@WebMvcTest(StockController.class) 
public class StockControllerTest {
  
  @MockBean
  StockService stockService;


  @Autowired// WebMvcTest -> MockMvc() -> Spring context
  private MockMvc mockMvc;

  @Test
  void testgetQuote() throws Exception {
    Quote aapl = Quote.builder()
              .c(1.11)
              .d(2.22)
              .dp(3.33)
              .h(4.44)
              .l(5.55)
              .o(6.66)
              .pc(7.77)
              .t(999)
              .build();

    Mockito.when(stockService.getQuote("AAPL")).thenReturn(aapl);

    mockMvc.perform(get("/stock/finnhub/api/v1/quote?symbol=AAPL"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data.c").value(1.11))
    .andExpect(jsonPath("$.data.d").value(2.22))
    .andExpect(jsonPath("$.data.dp").value(3.33))
    .andExpect(jsonPath("$.data.h").value(4.44))
    .andExpect(jsonPath("$.data.l").value(5.55))
    .andExpect(jsonPath("$.data.o").value(6.66))
    .andExpect(jsonPath("$.data.pc").value(7.77))
    .andExpect(jsonPath("$.data.t").value(999))
    .andDo(print());

  }

  @Test
  void testgetProfile2() throws Exception {
    Profile2 aapl = Profile2.builder()
            .country("US")
            .currency("USD")
            .estimateCurrency("USD")
            .exchange("NASDAY - ABC")
            .finnhubIndustry("Tech123")
            .ipo("1997-07-01")
            .logo("https://apple.com")
            .marketCapitalization(345678.123789)
            .name("Apple test")
            .phone("9753124680")
            .shareOutstanding(65432.09)
            .ticker("AAPL")
            .weburl("https://www.apple.abc")
            .build();

    Mockito.when(stockService.getProfile2("AAPL")).thenReturn(aapl);

    mockMvc.perform(get("/stock/finnhub/api/v1/profile2?symbol=AAPL"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value("000000"))
    .andExpect(jsonPath("$.message").value("OK"))
    .andExpect(jsonPath("$.data.country").value("US"))
    .andExpect(jsonPath("$.data.currency").value("USD"))
    .andExpect(jsonPath("$.data.estimateCurrency").value("USD"))
    .andExpect(jsonPath("$.data.exchange").value("NASDAY - ABC"))
    .andExpect(jsonPath("$.data.finnhubIndustry").value("Tech123"))
    .andExpect(jsonPath("$.data.ipo").value("1997-07-01"))
    .andExpect(jsonPath("$.data.logo").value("https://apple.com"))
    .andExpect(jsonPath("$.data.marketCapitalization").value(345678.123789))
    .andExpect(jsonPath("$.data.name").value("Apple test"))
    .andExpect(jsonPath("$.data.phone").value("9753124680"))
    .andExpect(jsonPath("$.data.shareOutstanding").value(65432.09))
    .andExpect(jsonPath("$.data.ticker").value("AAPL"))
    .andExpect(jsonPath("$.data.weburl").value("https://www.apple.abc"))
    .andDo(print());

  }

}
