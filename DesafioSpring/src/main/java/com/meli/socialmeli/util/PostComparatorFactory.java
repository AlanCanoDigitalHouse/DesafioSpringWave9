package com.meli.socialmeli.util;

import com.meli.socialmeli.model.Post;

import java.util.Comparator;

public class PostComparatorFactory {
  private static final String DATE_ASCENDING = "date_asc";
  private static final String DATE_DESCENDING = "date_desc";

  public static Comparator<Post> getComparator(String order) {
    if (order != null) {
      switch (order) {
        case DATE_ASCENDING:
          return (o1, o2) -> o1.getDate().isBefore(o2.getDate()) ? -1 : o1.getDate().isAfter(o2.getDate()) ? 1 : 0;
        case DATE_DESCENDING:
          return (o1, o2) -> o1.getDate().isBefore(o2.getDate()) ? 1 : o1.getDate().isAfter(o2.getDate()) ? -1 : 0;
        default:
          return Post::compareTo;
      }
    } else {
      return Post::compareTo;
    }
  }
}
