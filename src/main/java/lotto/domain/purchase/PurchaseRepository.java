package lotto.domain.purchase;

public interface PurchaseRepository {

    void save(Purchase purchase);

    int amount();
}
