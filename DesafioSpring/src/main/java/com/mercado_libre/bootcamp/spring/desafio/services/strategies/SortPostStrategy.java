package com.mercado_libre.bootcamp.spring.desafio.services.strategies;

import com.mercado_libre.bootcamp.spring.desafio.exceptions.StrategyException;
import com.mercado_libre.bootcamp.spring.desafio.models.NameOrder;
import com.mercado_libre.bootcamp.spring.desafio.sorter.Sorter;
import com.mercado_libre.bootcamp.spring.desafio.sorter.post.PostAscendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.post.PostDescendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.post.PostNoneSorterImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Service
public class SortPostStrategy {
    private Map<NameOrder, Sorter> strategies = new EnumMap<>(NameOrder.class);

    @PostConstruct
    public void onPostConstruct() {
        strategies.put(NameOrder.NAME_ASC, new PostAscendentSorterImpl());
        strategies.put(NameOrder.NAME_DESC, new PostDescendentSorterImpl());
        strategies.put(NameOrder.NONE, new PostNoneSorterImpl());
    }

    public Sorter getImplementation(String order) {
        try {
            return strategies.get(NameOrder.valueOf(order.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new StrategyException("El valor del campo order es inválido: [" + order + "]");
        }
    }
}
