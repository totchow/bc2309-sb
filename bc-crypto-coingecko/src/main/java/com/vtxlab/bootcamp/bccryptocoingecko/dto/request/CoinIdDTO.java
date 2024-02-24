package com.vtxlab.bootcamp.bccryptocoingecko.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoinIdDTO {
  
  private String coinId;

  // get uri back from Object to String
  public static String uriString(List<CoinIdDTO> coinIdDTOs) {

    if (coinIdDTOs == null)
    return "";

    String uriString = "&ids=";
    for (int i = 0; i < coinIdDTOs.size() - 1; i++) {
      uriString += coinIdDTOs.get(i).getCoinId() + ",";
    }

    uriString += coinIdDTOs.get(coinIdDTOs.size()-1).getCoinId();
    return uriString; // e.g. "&ids=bitcoin,ethereum"
  }
}
