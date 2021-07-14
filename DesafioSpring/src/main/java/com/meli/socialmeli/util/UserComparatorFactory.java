package com.meli.socialmeli.util;

import com.meli.socialmeli.model.User;

import java.util.Comparator;

public class UserComparatorFactory {
  private static final String ALPHA_ASCENDING = "name_asc";
  private static final String ALPHA_DESCENDING = "name_desc";

  public static Comparator<User> getComparator(String order) {
    if (order != null) {
      if (ALPHA_DESCENDING.equals(order)) {
        return (o1, o2) -> o1.getUserName().compareTo(o2.getUserName()) * -1;
      }
    }
    return User::compareTo;
  }
}
