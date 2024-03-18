package com.vtxlab.bootcamp.bcproductdata.model.dto;

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

}
