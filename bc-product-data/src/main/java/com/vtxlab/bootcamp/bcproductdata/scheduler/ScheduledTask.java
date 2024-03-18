package com.vtxlab.bootcamp.bcproductdata.scheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ExternalCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.coins.ProductCoinsEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ExternalStockProfile2Entity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ExternalStockQuoteEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStockListEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksDailyEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.stocks.ProductStocksEntity;
import com.vtxlab.bootcamp.bcproductdata.infra.ApiResponse;
import com.vtxlab.bootcamp.bcproductdata.model.dto.CoinDTO;
import com.vtxlab.bootcamp.bcproductdata.model.dto.Profile2DTO;
import com.vtxlab.bootcamp.bcproductdata.model.dto.QuoteDTO;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ExternalCoinsRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ProductCoinListRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.coins.ProductCoinsRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ExternalStockProfile2Repository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ExternalStockQuoteRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStockListRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStocksDailyRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.stocks.ProductStocksRepository;

@Component
public class ScheduledTask {
  
  @Autowired
  private ProductCoinListRepository productCoinListRepository;

  @Autowired
  private ProductStockListRepository productStockListRepository;

  @Autowired
  private ExternalCoinsRepository externalCoinsRepository;

  @Autowired
  private ProductCoinsRepository productCoinsRepository;

  @Autowired
  private ExternalStockQuoteRepository externalStockQuoteRepository;

  @Autowired
  private ExternalStockProfile2Repository externalStockProfile2Repository;

  @Autowired
  private ProductStocksRepository productStocksRepository;

  @Autowired
  private ProductStocksDailyRepository productStocksDailyRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.coingecko.coinsmarket}")
  private String coinsmarketuri;
  
  @Value("${api.finnhub.stockquote}")
  private String stockquoteuri;

  @Value("${api.finnhub.stockprofile2}")
  private String stockprofile2uri;

  @Scheduled(fixedRate = 60000) // 3000ms = 3s
  public void getExternalCoinsData() throws InterruptedException{

    List<String> coinIds = productCoinListRepository.findAllCoinCode();

    String ids = String.join(",",coinIds);
    String apiUrl = coinsmarketuri + ids;
    
    //ApiResponse<CoinDTO[]> coinData = restTemplate.getForObject(apiUrl, ApiResponse.class);
    ApiResponse<List<CoinDTO>> coinData 
      = restTemplate.exchange(apiUrl, HttpMethod.GET, null
      ,new ParameterizedTypeReference<ApiResponse<List<CoinDTO>>>() {})
      .getBody();
    
    List<CoinDTO> coinDTOs = coinData.getData();

    List<ExternalCoinsEntity> ecEntity 
      = coinDTOs.stream()
          .map(e -> {return ExternalCoinsEntity.builder()
                    .quoteDate(LocalDateTime.now())
                    .quoteCoinCode(e.getId())
                    .quoteCurrency("usd")
                    .name(e.getName())
                    .image(e.getImage())
                    .currentPrice(e.getCurrent_price())
                    .marketCap((double)e.getMarket_cap())
                    .marketCapRank(e.getMarket_cap_rank())
                    .fullyDilutedValuation((double)e.getFully_diluted_valuation())
                    .totalVolume((double)e.getTotal_volume())
                    .high24h(e.getHigh_24h())
                    .low24h(e.getLow_24h())
                    .priceChange24h(e.getPrice_change_24h())
                    .priceChangePercentage24h(e.getPrice_change_percentage_24h())
                    .marketCapChange24h((double)e.getMarket_cap_change_24h())
                    .marketCapChangePercentage24h(e.getMarket_cap_change_percentage_24h())
                    .circulatingSupply(e.getCirculating_supply())
                    .totalSupply(e.getTotal_supply())
                    .maxSupply(e.getMax_supply())
                    .ath(e.getAth())
                    .athChangePercentage(e.getAth_change_percentage())
                    .athDate(convertDateTime(e.getAth_date()))
                    .atl(e.getAtl())
                    .atlChangePercentage(e.getAtl_change_percentage())
                    .atlDate(convertDateTime(e.getAtl_date()))
                    .build();
          })
          .collect(Collectors.toList());

    for (ExternalCoinsEntity e: ecEntity) {
      externalCoinsRepository.save(e);
    }
  }

  @Scheduled(fixedRate = 60000) // 3000ms = 3s
  public void saveProductCoinsData() throws InterruptedException{
    List<ExternalCoinsEntity> ecEntity = externalCoinsRepository.findLatestCoinsData();

    List<ProductCoinsEntity> pcEntityList
      = ecEntity.stream()
        .map(e -> { 
          ProductCoinListEntity pcl = productCoinListRepository.findByCoinCode(e.getQuoteCoinCode());
          
          ProductCoinsEntity pc = ProductCoinsEntity.builder()
            .name(e.getName())
            .currentPrice(e.getCurrentPrice())
            .priceChangePercentage24h(e.getPriceChangePercentage24h())
            .marketCap(e.getMarketCap())
            .logo(e.getImage())
            .build();
          pc.setProductCoinListEntity(pcl);
          pcl.setProductCoinsEntity(pc);

          return pc;
        }).toList();

    for (ProductCoinsEntity p : pcEntityList) {
      if (productCoinsRepository.findByCoinId(p.getCoinId()) != null) {
        productCoinsRepository.updateCoinsData(p.getCurrentPrice(), p.getPriceChangePercentage24h(), p.getMarketCap(), p.getCoinId());
      } else {
        productCoinsRepository.save(p);
      }
    }
  }

  @Scheduled(fixedRate = 60000) // 60000ms = 60s
  public void getExternalStockData() throws InterruptedException{

    List<String> stockIds = productStockListRepository.findAllStockCode();

    for (String id: stockIds) {
      String quoteUrl = stockquoteuri + id;

      ApiResponse<QuoteDTO> quoteData 
      = restTemplate.exchange(quoteUrl, HttpMethod.GET, null
      ,new ParameterizedTypeReference<ApiResponse<QuoteDTO>>() {})
      .getBody();

      QuoteDTO quotedto = quoteData.getData();

      ExternalStockQuoteEntity esqEntity 
        = ExternalStockQuoteEntity.builder()
              .quoteDate(LocalDateTime.now())
              .quoteStockCode(id)
              .currentPrice(quotedto.getC())
              .priceChg(quotedto.getD())
              .priceChgPct(quotedto.getDp())
              .priceDayHigh(quotedto.getH())
              .priceDayLow(quotedto.getL())
              .pricePrevOpen(quotedto.getO())
              .pricePrevClose(quotedto.getPc())
              .build();

      String profile2Url = stockprofile2uri + id;

      ApiResponse<Profile2DTO> profile2Data 
        = restTemplate.exchange(profile2Url, HttpMethod.GET, null
          ,new ParameterizedTypeReference<ApiResponse<Profile2DTO>>() {})
          .getBody();
        
      Profile2DTO profile2dto = profile2Data.getData();

      ExternalStockProfile2Entity espEntity 
        = ExternalStockProfile2Entity.builder()
            .quoteDate(LocalDateTime.now())
            .quoteStockCode(id)
            .country(profile2dto.getCountry())
            .currency(profile2dto.getCurrency())
            .estimateCurrency(profile2dto.getEstimateCurrency())
            .exchange(profile2dto.getExchange())
            .finnhubIndustry(profile2dto.getFinnhubIndustry())
            .ipo(profile2dto.getIpo())
            .logo(profile2dto.getLogo())
            .marketCapitalization(profile2dto.getMarketCapitalization())
            .name(profile2dto.getName())
            .phone(profile2dto.getPhone())
            .shareOutstanding(profile2dto.getShareOutstanding())
            .ticker(profile2dto.getTicker())
            .weburl(profile2dto.getWeburl())
            .build();
      
      externalStockQuoteRepository.save(esqEntity);
      externalStockProfile2Repository.save(espEntity);
    }


  }

  @Scheduled(fixedRate = 60000) 
  public void saveProductStocksData() throws InterruptedException{
    List<ExternalStockQuoteEntity> quoteEntityList = externalStockQuoteRepository.findLatestStockQuote();
    List<ExternalStockProfile2Entity> p2EntityList = externalStockProfile2Repository.findLatestStockProfile2();

    List<ProductStocksEntity> psEntityList
      = quoteEntityList.stream()
        .map(e -> { 
          ProductStockListEntity psl = productStockListRepository.findByStockCode(e.getQuoteStockCode());
          ExternalStockProfile2Entity p2Entity = p2EntityList.stream().filter(s -> s.getQuoteStockCode().equals(e.getQuoteStockCode())).findFirst().get();
          
          ProductStocksEntity ps = ProductStocksEntity.builder()
            .name(p2Entity.getName())
            .currentPrice(e.getCurrentPrice())
            .priceChgPct(e.getPriceChgPct())
            .marketCap(p2Entity.getMarketCapitalization())
            .logo(p2Entity.getLogo())
            .build();
          ps.setStockListEntity(psl);
          psl.setProductStocksEntity(ps);

          return ps;
        }).toList();

    for (ProductStocksEntity p : psEntityList) {
      if (productStocksRepository.findByStockId(p.getStockId()) != null) {
        productStocksRepository.updateStocksData(p.getCurrentPrice(), p.getPriceChgPct(), p.getMarketCap(), p.getStockId());
      } else {
        productStocksRepository.save(p);
      }
    }
  }

  @Scheduled(cron = "0 0 6 * * TUE-SAT") 
  public void saveProductStocksDailyData() throws InterruptedException{

    List<ExternalStockQuoteEntity> quoteEntityList = externalStockQuoteRepository.findLatestStockQuote();

    List<ProductStocksDailyEntity> psdEntityList 
      = quoteEntityList.stream() //
          .map(e -> {
            ProductStockListEntity psl = productStockListRepository.findByStockCode(e.getQuoteStockCode());

            ProductStocksDailyEntity psd = ProductStocksDailyEntity.builder() //
              .tradeDate(e.getQuoteDate().toLocalDate())
              .dayHigh(e.getPriceDayHigh())
              .dayLow(e.getPriceDayLow())
              .dayOpen(e.getPricePrevOpen())
              .dayClose(e.getPricePrevClose())
              .build();

            psd.setStockListEntityDaily(psl);
            //productStocksDailyRepository.save(psd);
            //List<ProductStocksDailyEntity> psdList = productStocksDailyRepository.findByStockId(psd.getStockId());
            psl.updateStocksDailyEntity(psd);
            return psd;
          }).collect(Collectors.toList());

    productStocksDailyRepository.saveAll(psdEntityList);
  }

  @Scheduled(cron = "0 0 0 * * MON-SUN") 
  public void deleteDateby24hr() {
    externalCoinsRepository.deleteDataby24HR();
    externalStockQuoteRepository.deleteDataby24HR();
    externalStockProfile2Repository.deleteDataby24HR();
  }

  public static LocalDateTime convertDateTime(String datetime) {
    ZonedDateTime parse = 
      ZonedDateTime.parse(datetime, DateTimeFormatter.ISO_DATE_TIME)
        .withZoneSameInstant(ZoneId.systemDefault());
    return parse.toLocalDateTime();
  }
}
