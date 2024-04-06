package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vtxlab.bootcamp.bcproductdata.controller.ProductDataOperation;
import com.vtxlab.bootcamp.bcproductdata.dto.response.ProductDTO;

@Controller
@RequestMapping(value = "/data/api/v1")
public class FrontendController {
 
  @Autowired
  ProductDataOperation productDataOperation;  

  @CrossOrigin
  @GetMapping(value = "/display")
  public String display(Model model) { // pass by reference

    // List<ProductDTO> productdtos = productDataOperation.getProduct().getData();
    // model.addAttribute("products", productdtos);

    return "App"; // html
  }
}
