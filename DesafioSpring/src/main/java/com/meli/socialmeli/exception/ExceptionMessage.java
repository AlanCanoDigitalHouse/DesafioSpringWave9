package com.meli.socialmeli.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ExceptionMessage {

  private String errorMessage;
  private Map<String, String> errors;

  public ExceptionMessage(String errorMessage, Map<String, String> errors) {
    this.errorMessage = errorMessage;
    this.errors = errors;
  }
}
