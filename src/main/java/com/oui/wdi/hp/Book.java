package com.oui.wdi.hp;

import java.math.BigDecimal;

public class Book {

  private static final int UNIT_PRICE = 8;
  private String name;

  Book(String name) {
    this.name = name;
  }

  static BigDecimal unitPrice(){
    return BigDecimal.valueOf(UNIT_PRICE);
  }

  String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    return name != null ? name.equals(book.name) : book.name == null;
  }
}
