package com.meli.socialmeli.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.ProductDTO;
import com.meli.socialmeli.dto.request.NewPostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

  String GET_POST_OF_SELLERS_FOLLOWED_BY = "/products/followed/1/list";
  String GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_ASC = "/products/followed/1/list?order=date_asc";
  String GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_DESC = "/products/followed/1/list?order=date_desc";
  String NEW_POST = "/products/newpost";
  String NEW_PROMO_POST = "/products/newpromopost";
  String GET_PROMO_POST_COUNT = "/products/2/countPromo";
  String GET_PROMO_POSTS = "/products/2/list";
  NewPostRequest newPostRequest;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    ProductDTO productDTO = ProductDTO.builder()
            .productName("Teclado Gamer")
            .type("Gamer")
            .brand("Alienware")
            .color("Azul")
            .notes("Standard Edition")
            .build();
    newPostRequest = new NewPostRequest(2, LocalDate.now().minusDays(5), productDTO, 100, 2000.0, true, .15);
  }

  @Test
  void newPostTest() throws Exception {
    mockMvc.perform(post(NEW_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newPostRequest)))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  void findPostsOfSellersFollowedBy() throws Exception {
//    newPostTest();
    mockMvc.perform(get(GET_POST_OF_SELLERS_FOLLOWED_BY))
            .andDo(print())
            .andExpect(jsonPath("$.posts").isNotEmpty())
            //el segundo post es mas antiguo a dos semanas
            .andExpect(jsonPath("$.posts[1]").exists());
  }

  @Test
  void findPostsOfSellersFollowedByAsc() throws Exception {
//    newPostTest();
    mockMvc.perform(get(GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_ASC))
            .andDo(print())
            .andExpect(jsonPath("$.posts").isNotEmpty())
            //el segundo post es mas antiguo a dos semanas
            .andExpect(jsonPath("$.posts[0].date").value(LocalDate.now().minusMonths(2).toString()))
            .andExpect(jsonPath("$.posts[1].date").value(LocalDate.now().toString()));
  }

  @Test
  void findPostsOfSellersFollowedByDesc() throws Exception {
//    newPostTest();
    mockMvc.perform(get(GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_DESC))
            .andDo(print())
            .andExpect(jsonPath("$.posts").isNotEmpty())
            //el segundo post es mas antiguo a dos semanas
            .andExpect(jsonPath("$.posts[0].date").value(LocalDate.now().toString()))
            .andExpect(jsonPath("$.posts[1].date").value(LocalDate.now().minusMonths(2).toString()));

  }

  @Test
  void findPostsOfSellersFollowedByAll() throws Exception {
    findPostsOfSellersFollowedBy();
    findPostsOfSellersFollowedByAsc();
    findPostsOfSellersFollowedByDesc();
  }

  @Test
  void newPromoPostTest() throws Exception {
    mockMvc.perform(post(NEW_PROMO_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newPostRequest)))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  void newPromoPostCountTest() throws Exception {
    mockMvc.perform(get(GET_PROMO_POST_COUNT))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.promoproducts_count").value(1));
  }

  @Test
  void newPromoPostAndCountTest() throws Exception {
    newPromoPostTest();
    mockMvc.perform(get(GET_PROMO_POST_COUNT))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.promoproducts_count").value(2));
  }

  @Test
  void getPromoPostsTest() throws Exception {
    mockMvc.perform(get(GET_PROMO_POSTS))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.posts[*].hasPromo").value(true))
            .andExpect(jsonPath("$.posts[*].discount").isNotEmpty());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}