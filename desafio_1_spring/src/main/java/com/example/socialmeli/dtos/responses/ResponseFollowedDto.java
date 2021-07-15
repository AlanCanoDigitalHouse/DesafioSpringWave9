package com.example.socialmeli.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ResponseFollowedDto  extends ResponseUserDto{
    private List<ResponseUserDto> followed;
}
