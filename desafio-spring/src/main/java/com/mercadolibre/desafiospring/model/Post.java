package com.mercadolibre.desafiospring.model;

import com.mercadolibre.desafiospring.util.SortUtils;
import lombok.*;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Date date;
    private int category;
    private double price;
    private Product product;
    private double discount;

    public static final Map<String, Comparator<Post>> sortings = new HashMap<>() {{
        put(SortUtils.NAME_ASC_ORDER_QUERY_PARAM, (a, b) -> {
            return a.getProduct().getProductName()
                    .compareTo(b.getProduct().getProductName());
        });

        put(SortUtils.NAME_DESC_ORDER_QUERY_PARAM, (a, b) -> {
            return - a.getProduct().getProductName()
                    .compareTo(b.getProduct().getProductName());
        });

        put(SortUtils.DATE_ASC_ORDER_QUERY_PARAM, (a, b) -> {
            return a.getDate().compareTo(b.getDate());
        });

        put(SortUtils.DATE_DESC_ORDER_QUERY_PARAM, (a, b) -> {
            return - a.getDate().compareTo(b.getDate());
        });
    }};

    public Post(Date date, int category, double price, Product product) {
        this.date = date;
        this.category = category;
        this.price = price;
        this.product = product;
        this.discount = 0;
    }

    public double getPriceWithDiscount() {
        return this.price - this.discount * this.price;
    }
}
