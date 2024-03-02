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

  public static RoiDTO RoiCreate(Roi roi) {
    if (roi != null) {
      return new RoiDTO(roi.getTimes(), roi.getCurrency(), roi.getPercentage());
    } else
    return null;
  }
}
