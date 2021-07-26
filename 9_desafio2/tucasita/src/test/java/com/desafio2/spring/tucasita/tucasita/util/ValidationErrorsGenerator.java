package com.desafio2.spring.tucasita.tucasita.util;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorsGenerator {

    public static Map<String, String> getError(String key, String msgError){
        Map<String, String> result = new HashMap<>();
        result.put(key, msgError);
        return result;
    }

}
