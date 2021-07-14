package com.meli.socialmeli.util.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class SocialMeliConstantsTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void orderPatternTest() {
    Pattern compile = Pattern.compile("order=name_(asc|desc)");
    Assertions.assertTrue(compile.matcher("order=name_asc").matches());
    Assertions.assertTrue(compile.matcher("order=name_desc").matches());
    Assertions.assertFalse(compile.matcher("order=name_azc").matches());
    Assertions.assertFalse(compile.matcher("orrder=name_asc").matches());
    Assertions.assertFalse(compile.matcher("order=name_descc").matches());
  }
}