package com.oui.wdi.hp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.oui.wdi.hp.BookSeries.BOOK_SERIES_SIZE_DESCENDING;

public class BookSeriesPromotion implements PromotionEngine<BookSeries> {

    @Override
    public List<BookSeries> groupBy(List<Book> shopping) {
        List<BookSeries> bookSeriesList = new ArrayList<>();

        for (Book book : shopping) {
            if (bookSeriesList.isEmpty()) {
                addBook(bookSeriesList, book);
            } else {

                Optional<BookSeries> distinctBooks = bookSeriesList.stream()
                        .filter(bookSeries -> !bookSeries.contains(book))
                        .findFirst();

                if (distinctBooks.isPresent()) {
                    distinctBooks.get().add(book);
                } else {
                    addBook(bookSeriesList, book);
                }

                bookSeriesList.sort(BOOK_SERIES_SIZE_DESCENDING);

            }
        }

        return bookSeriesList;
    }

    private void addBook (List < BookSeries > distinctBookSeries, Book book){
        BookSeries bookSeries = new BookSeries();
        bookSeries.add(book);
        distinctBookSeries.add(bookSeries);
    }

}
