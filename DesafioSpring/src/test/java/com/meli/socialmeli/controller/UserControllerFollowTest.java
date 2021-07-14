package com.meli.socialmeli.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerFollowTest {

  @Autowired
  MockMvc mockMvc;

  static String ADD_FOLLOWER_REQUEST = "/users/2/follow/2";
  static String ADD_FOLLOWER_REQUEST_INVALID_IDS = "/users/10/follow/2";
  static String ADD_FOLLOWER_INVALID_REQUEST = "/users/1/follow/-1";
  static String ADD_FOLLOWER_INVALID_REQUEST_NULL = "/users/null/follow/-1";
  static String ADD_FOLLOWER_INVALID_MAPP_DONT_EXIST = "/user/null/follow/-1";
  static String REMOVE_FOLLOWER_REQUEST = "/users/1/unfollow/2";
  static String GET_FOLLOWERS_COUNT_REQUEST = "/users/2/followers/count";
  static String GET_FOLLOWERS_LIST = "/users/2/followers/list";


  @Test
  void testAddFollower() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_REQUEST))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void addFollowerInvalidTest() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_INVALID_REQUEST))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void addFollowerInvalidTestNull() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_INVALID_REQUEST_NULL))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void addFollowerInvalidIds() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_REQUEST_INVALID_IDS))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void methodDoesnExistTest() throws Exception {
    mockMvc.perform(
            post(ADD_FOLLOWER_INVALID_MAPP_DONT_EXIST))
            .andDo(print())
            .andExpect(status().isNotFound());
  }

  @Test
  void testRemoveFollower() throws Exception {
    mockMvc.perform(
            post(REMOVE_FOLLOWER_REQUEST))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void testRemoveFollowerMultipleTimes() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_LIST))
            .andDo(print());
    mockMvc.perform(
            post(REMOVE_FOLLOWER_REQUEST))
            .andDo(print())
            .andExpect(status().isOk());
    mockMvc.perform(
            post(REMOVE_FOLLOWER_REQUEST))
            .andDo(print())
            .andExpect(status().isOk());
    mockMvc.perform(get(GET_FOLLOWERS_LIST))
            .andDo(print());
  }

  @Test
  void getFollowersCount() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(2))
            .andExpect(status().isOk());
  }

  @Test
  void getFollowersCountInvalid() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").isNotEmpty())
            .andExpect(status().isOk());
  }

  @Test
  void getFollowersList() throws Exception {
    mockMvc.perform(get(GET_FOLLOWERS_LIST))
            .andDo(print())
            .andExpect(jsonPath("$.followers").exists())
            .andExpect(status().isOk());
  }

  @Test
  void addSameFollowerTest() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
    LinkedHashMap followersCountDTO = jsonToObj(mvcResult1.getResponse().getContentAsString());
    Object followers_count = followersCountDTO.get("followers_count");
    int expected = Integer.valueOf(followers_count.toString());
    testAddFollower();
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(expected))
            .andExpect(status().isOk());
    testAddFollower();
    mockMvc.perform(get(GET_FOLLOWERS_COUNT_REQUEST))
            .andDo(print())
            .andExpect(jsonPath("$.followers_count").value(expected))
            .andExpect(status().isOk());
  }

  private <T> T jsonToObj(String json) throws JsonProcessingException {
    TypeReference<T> typeReference = new TypeReference<>() {
    };
    return new ObjectMapper().readValue(json, typeReference);
  }
}
