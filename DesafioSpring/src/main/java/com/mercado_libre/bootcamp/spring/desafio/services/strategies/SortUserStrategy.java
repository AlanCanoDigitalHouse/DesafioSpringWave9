package com.mercado_libre.bootcamp.spring.desafio.services.strategies;

import com.mercado_libre.bootcamp.spring.desafio.exceptions.StrategyException;
import com.mercado_libre.bootcamp.spring.desafio.models.NameOrder;
import com.mercado_libre.bootcamp.spring.desafio.sorter.Sorter;
import com.mercado_libre.bootcamp.spring.desafio.sorter.user.UserAscendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.user.UserDescendentSorterImpl;
import com.mercado_libre.bootcamp.spring.desafio.sorter.user.UserNoneSorterImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Service
public class SortUserStrategy {

    private Map<NameOrder, Sorter> strategies = new EnumMap<>(NameOrder.class);

    @PostConstruct
    public void onPostConstruct() {
        strategies.put(NameOrder.NAME_ASC, new UserAscendentSorterImpl());
        strategies.put(NameOrder.NAME_DESC, new UserDescendentSorterImpl());
        strategies.put(NameOrder.NONE, new UserNoneSorterImpl());
    }

    public Sorter getImplementation(String order) {
        try {
            return strategies.get(NameOrder.valueOf(order.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new StrategyException("El valor del campo order es inválido: [" + order + "]");
        }
    }
}
