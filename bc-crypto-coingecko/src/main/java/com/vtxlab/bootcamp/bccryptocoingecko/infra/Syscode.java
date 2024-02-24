package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Syscode {
  OK("000000", "OK"),
  NOTFOUND("000001", "Resource Not Found"),
  REST_CLIENT_EXCEPTION("900000", "RestClientException - coingecko service is unavailable"),
  NULL_POINTER_EXCEPTION("900002","NullPointException"),
  GENERAL_EXCEPTION("999999","Unhandled Other Exception"),
  ;

  private String code;
  private String message;

}
