package com.vtxlab.bootcamp.bcstockfinnhub.model;

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
public class Quote {
  private double c;
  private double d;
  private double dp;
  private double h;
  private double l;
  private double o;
  private double pc;
  private int t;
}
