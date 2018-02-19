package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashSet;

import static com.oui.wdi.hp.Book.unitPrice;

public class DistinctBook extends HashSet<Book> {

  @Override
  public boolean add(Book book) {
    return this.stream().noneMatch(b->b.getName().equals(book.getName()))
      && super.add(book);
  }

  public BigDecimal calculPrice(){
    return unitPrice()
      .multiply(discount())
      .multiply(nbrBooks())
      .setScale(2, RoundingMode.HALF_EVEN);
  }

    private BigDecimal nbrBooks() {
        return BigDecimal.valueOf(size());
    }

    private BigDecimal discount(){

    switch (this.size()){
      case 2:return new BigDecimal(0.95);
      case 3:return new BigDecimal(0.90);
      case 4:return new BigDecimal(0.80);
      case 5:return new BigDecimal(0.75);
    }

    return new BigDecimal(1);

  }

}
