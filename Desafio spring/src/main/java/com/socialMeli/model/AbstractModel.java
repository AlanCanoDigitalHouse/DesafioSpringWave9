package com.socialMeli.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractModel {
    private int id;

    @JsonIgnore
    @JsonProperty(value = "user_password")
    public abstract String getModelClassName();
}
