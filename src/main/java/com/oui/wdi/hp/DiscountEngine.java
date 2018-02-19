package com.oui.wdi.hp;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountEngine {

    BigDecimal calculPriceWithDiscount(List<Book> shopping);

}
