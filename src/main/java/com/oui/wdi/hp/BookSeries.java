package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

import static com.oui.wdi.hp.Book.unitPrice;

public class BookSeries extends Promotion{

    public static final Comparator<BookSeries> BOOK_SERIES_SIZE_DESCENDING = (BookSeries o1, BookSeries o2) ->
            Integer.compare(o2.size(), o1.size());

    @Override
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
