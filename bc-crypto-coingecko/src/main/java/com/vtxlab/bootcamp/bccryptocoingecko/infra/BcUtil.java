package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class BcUtil {
  


  public static String getUrl(Scheme scheme, String domain, String uri) {
    return UriComponentsBuilder.newInstance()
      .scheme(scheme.toLowerCase()) //
      .host(domain) //
      .path(uri) //
      .toUriString(); // handle special character, \ ://
  }

  // public static String coingeckoUrl(String currency, String ids) {
  //   return domain + uri +
  //           "?vs_currency="+currency+
  //           "&ids="+ids+
  //           key;
  // }

}
