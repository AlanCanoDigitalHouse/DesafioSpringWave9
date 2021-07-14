package com.meli.socialmeli.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
public class ExceptionMessage {

  private String errorMessage;
  private Map<String, String> message;

  public ExceptionMessage(String errorMessage, Map<String, String> message) {
    this.errorMessage = errorMessage;
    this.message = message;
  }
}
