package com.example.socialmeli.dtos.response;

import com.example.socialmeli.dtos.response.FollowedListResponseDto;
import com.example.socialmeli.dtos.response.FollowersListResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnfollowDto {

   private FollowedListResponseDto followed;
   private FollowersListResponseDto followers;
}
