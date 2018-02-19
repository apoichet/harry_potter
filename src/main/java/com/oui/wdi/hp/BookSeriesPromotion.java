package com.oui.wdi.hp;

import java.util.ArrayList;
import java.util.List;

public class BookSeriesPromotion implements PromotionEngine<BookSeries> {

  @Override
    public List<BookSeries> buildPromo(List<Book> shopping){

      BookSeries bookSeries = new BookSeries();
      List<Book> remainingBooks = new ArrayList<>();

      for (Book book : shopping) {
           if (!bookSeries.contains(book)){
             bookSeries.add(book);
           }
           else {
             remainingBooks.add(book);
           }
      }

      List<BookSeries> bookSeriesList = new ArrayList<>();
      bookSeriesList.add(bookSeries);

      if (!remainingBooks.isEmpty()){
        bookSeriesList.addAll(buildPromo(remainingBooks));
      }

      return bookSeriesList;
    }

}
