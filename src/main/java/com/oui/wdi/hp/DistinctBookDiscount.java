package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class DistinctBookDiscount implements DiscountEngine{

  @Override
  public BigDecimal calculPriceWithDiscount(List<Book> shopping) {

    long nbrBookDistinct = shopping.stream().distinct().count();
    Collection<List<DistinctBook>> discountProposals = new ArrayList<>();

    for (long i = 0; i < nbrBookDistinct; i++) {
      discountProposals.add(partition(shopping, i));
    }

    return discountProposals.stream()
      .map(this::calculPrice)
      .min(Comparator.naturalOrder())
      .orElse(BigDecimal.ZERO);
  }

  private List<DistinctBook> partition(List<Book> shopping, long maxBook){

    DistinctBook distinctBook = new DistinctBook();
    List<Book> remainingBooks = new ArrayList<>();

    for (Book book : shopping) {
      final boolean addDistinctBook = distinctBook.size() <= maxBook && distinctBook.add(book);
      if (!addDistinctBook){
        remainingBooks.add(book);
      }
    }

    List<DistinctBook> distinctBookList = new ArrayList<>();
    distinctBookList.add(distinctBook);

    if (!remainingBooks.isEmpty()){
      distinctBookList.addAll(partition(remainingBooks, maxBook));
    }

    return distinctBookList;
  }

  private BigDecimal calculPrice(List<DistinctBook> distinctBooks){
    return distinctBooks.stream()
      .map(DistinctBook::calculPrice)
      .reduce(BigDecimal::add)
      .orElse(BigDecimal.ZERO);
  }

}
