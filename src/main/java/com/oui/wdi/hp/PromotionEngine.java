package com.oui.wdi.hp;

import java.util.List;

public interface PromotionEngine<PROMO extends Promotion> {

    List<PROMO> buildPromo(List<Book> shopping);

}
