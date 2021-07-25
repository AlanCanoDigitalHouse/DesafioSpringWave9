package com.mercadolibre.desafio_testing.util;

import com.fasterxml.jackson.databind.*;

public class FileUtils {
    public static final ObjectWriter objectMapper = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer().withDefaultPrettyPrinter();
}
