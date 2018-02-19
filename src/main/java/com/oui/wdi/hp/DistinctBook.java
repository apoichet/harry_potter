package com.oui.wdi.hp;

import static com.oui.wdi.hp.Book.unitPrice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class DistinctBook extends HashSet<Book> {

  private long limitSize;

  DistinctBook(long limitSize) {
     this.limitSize = limitSize;
  }

  @Override
  public boolean add(Book newBook) {
    return stream().noneMatch(book -> book.equals(newBook))
      && limitSize > this.size()
      && super.add(newBook);
  }

  BigDecimal calculPrice(){
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
