package com.socialMeli.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserModel extends AbstractModel {
    private String userName;
    private List<Long> followed;

    @Override
    public String getModelClassName() {
        return "user";
    }
}
