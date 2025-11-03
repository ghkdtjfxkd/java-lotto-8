package lotto.application.service.purchase;

import lotto.domain.lottoGame.LottoTicket;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.purchase.Purchase;
import lotto.domain.purchase.PurchaseRepository;

public class LottoPurchaseCommandServiceImpl implements LottoPurchaseCommandService {

    private final PurchaseRepository purchaseRepository;
    private final LottoTicketRepository lottoTicketRepository;

    private final PickLottoNumbersStrategy pickLottoNumbersStrategy;

    public LottoPurchaseCommandServiceImpl(PurchaseRepository purchaseRepository,
                                           LottoTicketRepository lottoTicketRepository,
                                           PickLottoNumbersStrategy pickLottoNumbersStrategy) {
        this.purchaseRepository = purchaseRepository;
        this.lottoTicketRepository = lottoTicketRepository;
        this.pickLottoNumbersStrategy = pickLottoNumbersStrategy;
    }

    @Override
    public void purchaseLottoTicket(String inputMoney) {
        Purchase purchase = purchaseFrom(inputMoney);
        LottoTicket lottoTicket = LottoTicket.from(purchase.quantity(), pickLottoNumbersStrategy);
        lottoTicketRepository.save(lottoTicket);
    }

    private Purchase purchaseFrom(String inputMoney) {
        Purchase purchase = Purchase.from(inputMoney);
        purchaseRepository.save(Purchase.from(inputMoney));
        return purchase;
    }
}
