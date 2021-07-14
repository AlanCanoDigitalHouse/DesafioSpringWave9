package com.meli.socialmeli.exception;

public class SocialMeliException extends Exception {
  public SocialMeliException(String message) {
    super(message);
  }

  public SocialMeliException(String message, Throwable cause) {
    super(message, cause);
  }
}
