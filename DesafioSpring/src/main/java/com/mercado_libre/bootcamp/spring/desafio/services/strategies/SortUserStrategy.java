package com.mercado_libre.bootcamp.spring.desafio.services.strategies;

import com.mercado_libre.bootcamp.spring.desafio.models.UserOrder;
import com.mercado_libre.bootcamp.spring.desafio.sorter.AscendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.DescendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.NoneSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.Sorter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Service
public class SortUserStrategy {

    private Map<UserOrder, Sorter> comparators = new EnumMap<>(UserOrder.class);

    @PostConstruct
    public void onPostConstruct() {
        comparators.put(UserOrder.NAME_ASC, new AscendentSorterImpl());
        comparators.put(UserOrder.NAME_DESC, new DescendentSorterImpl());
        comparators.put(UserOrder.NONE, new NoneSorterImpl());
    }

    public Sorter getImplementation(String order) {
        try {
            return comparators.get(UserOrder.valueOf(order.toUpperCase()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
