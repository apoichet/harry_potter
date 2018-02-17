package com.oui.wdi.hp;

import java.math.BigDecimal;

public class Book {

  private static final int UNIT_PRICE = 8;
  private String name;

  public Book(String name) {
    this.name = name;
  }

  public static BigDecimal unitPrice(){
    return BigDecimal.valueOf(UNIT_PRICE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Book)) return false;

    Book book = (Book) o;

    return name != null && name.equals(book.name);
  }

  public BigDecimal getPrice() {
    return BigDecimal.valueOf(UNIT_PRICE);
  }

}
