package com.example.desafiotesting.units.controller;


import com.example.desafiotesting.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class CalculateControllerTest {

    @Test
    void calculateMetersCorrectAnswer() {
        //given
        Integer width = 10;
        Integer length = 15;
        RoomDTO room = new RoomDTO("test", 10, 15);
        Integer expected = width*length;

        //when
        Integer result = room.getSquareFeet();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void calculateMetersBadAnswer() {
        //given
        Integer width = 30;
        Integer length = 20;
        RoomDTO room = new RoomDTO("test", 20, 20);
        Integer expected = width*length;

        //when
        Integer result = room.getSquareFeet();

        //then
        Assertions.assertNotEquals(expected, result);
    }
}


