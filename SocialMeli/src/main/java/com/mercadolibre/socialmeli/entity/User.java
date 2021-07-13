package com.mercadolibre.socialmeli.entity;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User extends UserBase{
    List<Integer> followers = new ArrayList<>();
    List<Integer> followed = new ArrayList<>();
    List<Integer> posts = new ArrayList<>();
}
