package io.study.deneb.controller;

import io.study.deneb.security.SecurityConfigure;
import io.study.deneb.service.TokenService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HomeController.class, AuthController.class})
@Import({SecurityConfigure.class, TokenService.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeControllerTest {

  @Autowired
  MockMvc mockMvc;

  final String username = "deneb";
  final String password = "1234";

  @Test
  @Order(1)
  void accessHomeWhenUnauthenticated() throws Exception {
    mockMvc.perform(get("/"))
     .andExpect(status().isUnauthorized());
  }

  @Test
  @Order(2)
  void accessHomeWhenAuthenticated() throws Exception {
    MvcResult result = mockMvc.perform(
        post("/token")
          .with(httpBasic(username, password))
      )
      .andExpect(status().isOk())
      .andReturn();

    String token = result.getResponse().getContentAsString();

    mockMvc.perform(get("/")
        .header("Authorization", "Bearer " + token)
      )
     .andExpect(status().isOk());
  }

  @Test
  @WithMockUser // user mocking
  @Order(3)
  void accessHomeWithMockUser() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
  }

}