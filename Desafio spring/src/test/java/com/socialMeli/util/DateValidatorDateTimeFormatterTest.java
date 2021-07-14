package com.socialMeli.util;

import com.socialMeli.utils.DateValidatorDateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateValidatorDateTimeFormatterTest {
    @Test
    public void validateGoodDate(){
        Assertions.assertTrue(DateValidatorDateTimeFormatter.isValid("31-12-2021"));
    }
    @Test
    public void validateGoodDateBisiesto(){
        Assertions.assertTrue(DateValidatorDateTimeFormatter.isValid("29-02-2020"));
    }
    @Test
    public void validateBadDate(){
        Assertions.assertFalse(DateValidatorDateTimeFormatter.isValid("10-13-2021"));
    }
}
