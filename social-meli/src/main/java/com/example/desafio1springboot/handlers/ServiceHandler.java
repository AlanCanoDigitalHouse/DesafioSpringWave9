package com.example.desafio1springboot.handlers;

import com.example.desafio1springboot.exceptions.OrderNotValidException;

public class ServiceHandler {

    public static void checkIfOrder_Validation(String order, String orderToCheck1, String orderToCheck2) throws OrderNotValidException {
        if(!order.equals(orderToCheck1) && !order.equals(orderToCheck2))
            throw new OrderNotValidException();
    }

}
