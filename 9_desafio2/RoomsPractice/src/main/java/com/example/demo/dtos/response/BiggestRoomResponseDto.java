package com.example.demo.dtos.response;

import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.request.RoomRequestDto;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BiggestRoomResponseDto {

    private RoomRequestDto room;
}
