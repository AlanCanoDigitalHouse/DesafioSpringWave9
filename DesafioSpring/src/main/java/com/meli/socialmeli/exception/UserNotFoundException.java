package com.meli.socialmeli.exception;

public class UserNotFoundException extends SocialMeliException {
  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
