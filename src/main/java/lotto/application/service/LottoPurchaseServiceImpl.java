package lotto.application.service;

import java.util.List;
import lotto.domain.lottoGame.LottoTicket;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.purchase.Purchase;
import lotto.domain.purchase.PurchaseRepository;
import lotto.application.dto.PurchasedLotto;

public class LottoPurchaseServiceImpl implements LottoPurchaseService {

    private final LottoTicketRepository lottoTicketRepository;
    private final PurchaseRepository purchaseRepository;
    private final PickLottoNumbersStrategy pickLottoNumbersStrategy;

    public LottoPurchaseServiceImpl(LottoTicketRepository lottoTicketRepository,
                                    PurchaseRepository purchaseRepository,
                                    PickLottoNumbersStrategy pickLottoNumbersStrategy) {
        this.lottoTicketRepository = lottoTicketRepository;
        this.purchaseRepository = purchaseRepository;
        this.pickLottoNumbersStrategy = pickLottoNumbersStrategy;
    }

    @Override
    public void purchaseLottoTicket(String inputMoney) {
        Purchase purchase = Purchase.from(inputMoney);

        LottoTicket.from(purchase.quantity(), pickLottoNumbersStrategy);
    }

    @Override
    public List<PurchasedLotto> LottoGames() {
        return List.of();
    }
}
