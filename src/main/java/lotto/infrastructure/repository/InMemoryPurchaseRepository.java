package lotto.infrastructure.repository;

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
    public int amount() {
        return purchase.amount();
    }
}
