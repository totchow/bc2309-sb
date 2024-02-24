package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import org.springframework.web.util.UriComponentsBuilder;

public class BcUtil {
  


  public static String getUrl(Scheme scheme, String domain, String uri) {
    return UriComponentsBuilder.newInstance()
      .scheme(scheme.toLowerCase()) //
      .host(domain) //
      .path(uri) //
      .toUriString(); // handle special character, \ ://
  }



}
