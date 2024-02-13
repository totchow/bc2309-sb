package com.vtxlab.bootcamp.bootcampsbcalculator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bootcampsbcalculator.controller.impl.CalculatorController;
import com.vtxlab.bootcamp.bootcampsbcalculator.dto.Input;
import com.vtxlab.bootcamp.bootcampsbcalculator.service.CalculatorService;
import com.vtxlab.bootcamp.infra.Operation;

@WebMvcTest(CalculatorController.class) 
public class CalculatorControllerTest {
  
  @MockBean
  CalculatorService calService;

  @Autowired // WebMvcTest -> MockMvc() -> Spring context
  private MockMvc mockMvc;

  @Test
  void testparamCal() throws Exception {
    Mockito.when(calService.calculate(3.0,7.0,Operation.mul)).thenReturn("21.0");

    mockMvc.perform(get("/cal/api/v1/get/?x=3.0&y=7.0&operation=mul"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.x").value("3.0"))
    .andExpect(jsonPath("$.y").value("7.0"))
    .andExpect(jsonPath("$.operation").value("mul"))
    .andExpect(jsonPath("$.result").value("21.0"))
    .andDo(print());
  }

  @Test
  void testpostCal() throws Exception {
    Mockito.when(calService.calculate(10.0,3.0,Operation.add)).thenReturn("13.0");

    Input input = new Input("10.0", "3.0", "add");
    // Convert an object to String value (JSON)
    String contentStr = new ObjectMapper().writeValueAsString(input);
    // Pretend post to make call
    mockMvc.perform(post("/cal/api/v1/post")
      .contentType(MediaType.APPLICATION_JSON)
      .content(contentStr))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
      .andExpect(jsonPath("$.x").value("10.0"))
      .andExpect(jsonPath("$.y").value("3.0"))
      .andExpect(jsonPath("$.operation").value("add"))
      .andExpect(jsonPath("$.result").value("13.0"))
      .andDo(print());
  }

  @Test
  void testpathCal() throws Exception {
    Mockito.when(calService.calculate(10.0,3.0,Operation.div)).thenReturn("3.33333");

    mockMvc.perform(get("/cal/api/v1/get/{x}/{y}/{operation}","10.0","3.0","div"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.x").value("10.0"))
    .andExpect(jsonPath("$.y").value("3.0"))
    .andExpect(jsonPath("$.operation").value("div"))
    .andExpect(jsonPath("$.result").value("3.33333"))
    .andDo(print());
  }

  @Test
  void testinvalid() throws Exception {

    // invalid operation by RequestParam
    mockMvc.perform(get("/cal/api/v1/get/?x=3.0&y=7.0&operation=abc"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value(9))
    .andExpect(jsonPath("$.message").value("Invalid Input."))
    .andDo(print());

    // invalid x by PathVariable
    mockMvc.perform(get("/cal/api/v1/get/{x}/{y}/{operation}","abc","3.0","div"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
    .andExpect(jsonPath("$.code").value(9))
    .andExpect(jsonPath("$.message").value("Invalid Input."))
    .andDo(print());

    // invalid y by Post
    Input input = new Input("10.0", "abc", "add");
    String contentStr = new ObjectMapper().writeValueAsString(input);
    mockMvc.perform(post("/cal/api/v1/post")
      .contentType(MediaType.APPLICATION_JSON)
      .content(contentStr))
      .andExpect(jsonPath("$.code").value(9))
      .andExpect(jsonPath("$.message").value("Invalid Input."))
      .andDo(print());

    // Divide by 0 , Arithmetic Exception
    mockMvc.perform(get("/cal/api/v1/get/?x=10.0&y=0&operation=abc"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // assert
      .andExpect(jsonPath("$.code").value(9))
      .andExpect(jsonPath("$.message").value("Invalid Input."))
      .andDo(print());
  }
}
