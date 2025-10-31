package lotto.infrastructure.repository;

import lotto.application.dto.PurchaseAmountDto;
import lotto.application.dto.PurchaseQuantityDto;
import lotto.domain.purchase.Purchase;
import lotto.domain.purchase.PurchaseRepository;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    private Purchase purchase;

    // Command
    @Override
    public void save(Purchase purchase) {
        this.purchase = purchase;
    }

    // Query
    @Override
    public PurchaseAmountDto amount() {
        return new PurchaseAmountDto(purchase.amount());
    }

    @Override
    public PurchaseQuantityDto quantity() {
        return new PurchaseQuantityDto(purchase.quantity());
    }
}
