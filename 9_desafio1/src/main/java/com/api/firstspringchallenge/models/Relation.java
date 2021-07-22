package com.api.firstspringchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Relation {
    private User follower;
    private User followed;
}
