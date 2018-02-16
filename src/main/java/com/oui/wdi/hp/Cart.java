package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.util.*;

import static com.oui.wdi.hp.BookBatch.BOOK_BATCH_SIZE_DESCENDING;

public class Cart {

    List<Book> books = new ArrayList<>();

    public BigDecimal calculatePrice(){
        return partition(books).stream()
                .map(BookBatch::calculPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


    private List<BookBatch> partition(List<Book> books) {
        List<BookBatch> distinctBookBatchs = new ArrayList<>();

        for (Book book : books) {
            if (distinctBookBatchs.isEmpty()){
                addBook(distinctBookBatchs, book);
            }
            else{

                Optional<BookBatch> distinctBooks = distinctBookBatchs.stream()
                        .filter(bookBatch -> !bookBatch.contains(book))
                        .findFirst();

                if (distinctBooks.isPresent()){
                    distinctBooks.get().add(book);
                }
                else {
                    addBook(distinctBookBatchs, book);
                }

                distinctBookBatchs.sort(BOOK_BATCH_SIZE_DESCENDING);

            }
        }

        return distinctBookBatchs;
    }


    private void addBook(List<BookBatch> distinctBookBatchs, Book book) {
        BookBatch bookBatch = new BookBatch();
        bookBatch.add(book);
        distinctBookBatchs.add(bookBatch);
    }

}
