package com.socialMeli.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostModel extends AbstractModel {
    private int userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private ProductModel detail;
    private int category;
    private double price;

    private Boolean hasPromo;
    private Double discount;


    @Builder
    public PostModel(int id, int userId, Date date, ProductModel detail, int category, double price, boolean hasPromo, double discount) {
        super(id);
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    @Override
    public String getModelClassName() {
        return "post";
    }

    //Needed because Jackson uses a default constructor other try is use @JsonTypeInfo and @JsonSubTypes
    //But after 2 hours i cant make this functional :(
    private PostModel() {
        super();
    }
}
