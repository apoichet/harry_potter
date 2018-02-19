package com.oui.wdi.hp;

import static java.util.stream.LongStream.rangeClosed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DistinctBookDiscount implements DiscountEngine{

  @Override
  public BigDecimal calculPriceWithDiscount(List<Book> shopping) {

    long nbrBookDistinct = shopping.stream()
      .map(Book::getName)
      .distinct()
      .count();

    return rangeClosed(1, nbrBookDistinct)
      .mapToObj(value -> getProposals(shopping, value))
      .map(this::calculPrice)
      .min(Comparator.naturalOrder())
      .orElse(BigDecimal.ZERO);

  }

  private List<DistinctBook> getProposals(List<Book> shopping, long maxBook){

    DistinctBook distinctBook = new DistinctBook(maxBook);
    List<Book> remainingBooks = new ArrayList<>();

    for (Book book : shopping) {
      final boolean addDistinctBook = distinctBook.add(book);
      if (!addDistinctBook){
        remainingBooks.add(book);
      }
    }

    List<DistinctBook> distinctBookList = new ArrayList<>();
    distinctBookList.add(distinctBook);

    if (!remainingBooks.isEmpty()){
      distinctBookList.addAll(getProposals(remainingBooks, maxBook));
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
