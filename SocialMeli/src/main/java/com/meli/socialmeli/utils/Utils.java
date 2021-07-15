package com.meli.socialmeli.utils;

import com.meli.socialmeli.dto.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
