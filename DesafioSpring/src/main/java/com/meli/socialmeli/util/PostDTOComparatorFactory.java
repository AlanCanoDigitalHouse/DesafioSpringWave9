package com.meli.socialmeli.util;

import com.meli.socialmeli.dto.PostDTO;

import java.util.Comparator;

public class PostDTOComparatorFactory {
  private static final String DATE_ASCENDING = "date_asc";
  private static final String DATE_DESCENDING = "date_desc";

  public static Comparator<PostDTO> getComparator(String order) {
    if (order != null) {
      switch (order) {
        case DATE_ASCENDING:
          return (o1, o2) -> o1.getDate().isBefore(o2.getDate()) ? -1 : o1.getDate().isAfter(o2.getDate()) ? 1 : 0;
        case DATE_DESCENDING:
          return (o1, o2) -> o1.getDate().isBefore(o2.getDate()) ? 1 : o1.getDate().isAfter(o2.getDate()) ? -1 : 0;
        default:
          return PostDTO::compareTo;
      }
    } else {
      return PostDTO::compareTo;
    }
  }
}
