package com.vtxlab.bootcamp.bccryptocoingecko.dto.response;

import com.vtxlab.bootcamp.bccryptocoingecko.model.Roi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoiDTO {

  private double times;
  private String currency;
  private double percentage;

  public RoiDTO(Roi roi) {
    if (roi != null) {
      this.times = roi.getTimes();
      this.currency = roi.getCurrency();
      this.percentage = roi.getPercentage();
    }
  }
}
