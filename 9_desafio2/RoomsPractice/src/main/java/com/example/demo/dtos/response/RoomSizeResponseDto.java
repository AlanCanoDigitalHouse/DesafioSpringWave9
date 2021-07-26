package com.example.demo.dtos.response;

import com.example.demo.dtos.request.RoomRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSizeResponseDto {

    private List<RoomRequestDto> roomList;
}
