package com.vtxlab.bootcamp.bcproductdata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class QuoteDTO {

  private double c;
  private double d;
  private double dp;
  private double h;
  private double l;
  private double o;
  private double pc;
  private int t;

}
