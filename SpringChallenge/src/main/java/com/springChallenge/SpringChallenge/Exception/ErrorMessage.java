package com.springChallenge.SpringChallenge.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private Integer status;
    private String error;
    private Map<String, String> messages;
}
