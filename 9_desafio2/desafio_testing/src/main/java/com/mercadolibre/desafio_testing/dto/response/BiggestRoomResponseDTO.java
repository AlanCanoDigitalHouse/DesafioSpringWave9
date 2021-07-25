package com.mercadolibre.desafio_testing.dto.response;

import lombok.*;

@Getter
@Setter
public class BiggestRoomResponseDTO {
    private String big_room;

    public BiggestRoomResponseDTO(String biggerRoom) {
        this.big_room = biggerRoom;
    }
}
