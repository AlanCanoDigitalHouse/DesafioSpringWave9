package com.example.desafiospring;

import com.example.desafiospring.utils.Factory;
import org.junit.jupiter.api.Test;

public class TestFactory {
    @Test
    void testGeneratedNumber(){}

    @Test
    void testGeneratedProduct(){
        System.out.println(Factory.generatePost());
    }
    @Test
    void testGeneratedUsers(){
        System.out.println(Factory.generateUsers());
    }
}
