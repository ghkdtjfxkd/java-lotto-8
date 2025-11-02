package lotto.infrastructure.repository;

import lotto.domain.purchase.Purchase;
import lotto.domain.purchase.PurchaseRepository;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    private Purchase purchase;

    @Override
    public void save(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public int amount() {
        return purchase.amount();
    }
}
