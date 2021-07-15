package com.example.DesafioSpring.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter


public class Buyer extends Users{

    //private List<Integer> following;

    public Buyer(Integer id, String name){
        super(id, name);
    }

}
