package com.meli.socialmeli.utils;

import com.meli.socialmeli.dto.Follower;
import com.meli.socialmeli.dto.FollowerB;
import com.meli.socialmeli.dto.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static List<PostDTO> postsSorter(List<PostDTO> list){
        LOGGER.info("Ordenamiento de fecha descendente.");
        list = list.stream().sorted(Comparator.comparing(PostDTO::getDate).reversed()).collect(Collectors.toList());
        return list;
    }

    public static List<Follower> sorter(List<Follower> list, String order) {

        List<Follower> bandera;
        if (order.equals(Constant.ORDERNAMIENTO_ASC)) {
            LOGGER.info("Ordenamiento ascendente.");
            bandera = list.stream()
                    .sorted(Comparator.comparing(Follower::getUserName))
                    .collect(Collectors.toList());
            //Collections.sort(listb);

        } else {
            LOGGER.info("Ordenamiento descendente.");
            bandera = list.stream()
                    .sorted(Comparator.comparing(Follower::getUserName).reversed())
                    .collect(Collectors.toList());
        }
        return bandera;
    }
}
