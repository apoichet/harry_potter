package com.oui.wdi.hp;

import java.math.BigDecimal;

public class Book {

  private static final int UNIT_PRICE = 8;
  private String name;
  private BigDecimal price;

  public Book(String name) {
    this.name = name;
    this.price = new BigDecimal(UNIT_PRICE);
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

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

}
