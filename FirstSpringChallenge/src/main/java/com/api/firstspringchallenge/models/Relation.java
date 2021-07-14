package com.api.firstspringchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class Relation {
    private User follower;
    private User followed;
}
