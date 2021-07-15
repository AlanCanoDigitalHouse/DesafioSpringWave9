package com.mercadolibre.desafiospring.model;

import com.mercadolibre.desafiospring.util.SortUtils;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class User {
    private int id;
    private String name;
    public static final Map<String, Comparator<User>> sortings = new HashMap<>() {{
        put(SortUtils.NAME_ASC_ORDER_QUERY_PARAM, (a, b) -> {
            return a.getName().compareTo(b.getName());
        });

        put(SortUtils.NAME_DESC_ORDER_QUERY_PARAM, (a, b) -> {
            return - a.getName().compareTo(b.getName());
        });
    }};
}
