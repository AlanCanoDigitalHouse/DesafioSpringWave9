package com.example.demo.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@AllArgsConstructor
@Data
public class SuccesResponse {

    public String message;
}
