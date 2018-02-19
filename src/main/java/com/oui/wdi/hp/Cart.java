package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Book> books = new ArrayList<>();
    private DiscountEngine discountEngine;

    public Cart(List<Book> books) {
        this.books.addAll(books);
        discountEngine = new DistinctBookDiscount();
    }

    public BigDecimal calculPrice(){
        return discountEngine.calculPriceWithDiscount(books)
          .setScale(2, RoundingMode.HALF_EVEN);
    }

}
