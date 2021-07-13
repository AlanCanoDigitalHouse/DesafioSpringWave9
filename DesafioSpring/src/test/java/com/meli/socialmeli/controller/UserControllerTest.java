package com.meli.socialmeli.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  static String ADD_FOLLOWER_REQUEST = "/users/1/follow/2";
  static String GET_FOLLOWERS_COUNT_REQUEST = "/users/2/followers/count";
  static String GET_FOLLOWERS_LIST = "/users/2/followers/list";
  static String GET_FOLLOWED_LIST = "/users/1/followed/list";


  @BeforeEach
  void setUp() {
  }

  @Test
  void testAddFollower() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_REQUEST))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void getFollowersCount() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(0))
            .andExpect(status().isOk());
  }

  @Test
  void getFollowersCountAndIncrementAndCountAgain() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
//            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(0))
            .andExpect(status().isOk());
    mockMvc.perform(
            post(ADD_FOLLOWER_REQUEST))
//            .andDo(print())
            .andExpect(status().isOk());
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(1))
            .andExpect(status().isOk());
  }

  @Test
  void getFollowersList() throws Exception {
    testAddFollower();
    mockMvc.perform(get(GET_FOLLOWERS_LIST))
            .andDo(print())
            .andExpect(jsonPath("$.followers").exists())
            .andExpect(status().isOk());

  }

  @Test
  void findUsersFollwedBy() throws Exception {
    mockMvc.perform(get(GET_FOLLOWED_LIST))
            .andDo(print())
            .andExpect(jsonPath("$.userId").value(1))
            .andExpect(jsonPath("$.followed[0].userId").value(2));
  }
}