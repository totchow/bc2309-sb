package com.vtxlab.bootcamp.infra;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
  
  private String x;
  private String y;
  private String operation;
  private String result;

}
