package com.meli.tasaciones.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class MetrosCuadradosErrorMessage {
  private final String errorMessage;
  private final Map<String, String> errors;

  public MetrosCuadradosErrorMessage(String errorMessage, Map<String, String> errors) {
    this.errorMessage = errorMessage;
    this.errors = errors;
  }
}
