package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;

import static com.oui.wdi.hp.Book.unitPrice;

public class BookBatch extends ArrayList<Book>{

    public static final Comparator<BookBatch> BOOK_BATCH_SIZE_DESCENDING = (BookBatch o1, BookBatch o2) ->
            Integer.compare(o2.size(), o1.size());

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
