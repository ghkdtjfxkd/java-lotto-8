package lotto.domain.purchase;

import lotto.application.dto.PurchaseAmountDto;
import lotto.application.dto.PurchaseQuantityDto;

public interface PurchaseRepository {

    void save(Purchase purchase);

    PurchaseQuantityDto quantity();
    PurchaseAmountDto amount();
}
