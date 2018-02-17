package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Book> books;
    private PromotionEngine<BookSeries> promotionEngine;

    public Cart(List<Book> books) {
        this.books = new ArrayList<>();
        promotionEngine = new BookSeriesPromotion();
        this.books.addAll(books);
    }

    public BigDecimal calculPrice(){
        return findPromotion().stream()
                .map(BookSeries::calculPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private List<BookSeries> findPromotion(){
        return promotionEngine.groupBy(books);
    }
}
