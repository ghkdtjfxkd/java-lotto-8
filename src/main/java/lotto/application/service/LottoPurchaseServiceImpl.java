package lotto.application.service;

import java.util.List;
import lotto.domain.lottoGame.LottoTicket;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.purchase.Purchase;
import lotto.domain.purchase.PurchaseRepository;
import lotto.application.dto.PurchasedLottoDto;

public class LottoPurchaseServiceImpl implements LottoPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final LottoTicketRepository lottoTicketRepository;

    private final PickLottoNumbersStrategy pickLottoNumbersStrategy;

    public LottoPurchaseServiceImpl(PurchaseRepository purchaseRepository,
                                    LottoTicketRepository lottoTicketRepository,
                                    PickLottoNumbersStrategy pickLottoNumbersStrategy) {
        this.purchaseRepository = purchaseRepository;
        this.lottoTicketRepository = lottoTicketRepository;
        this.pickLottoNumbersStrategy = pickLottoNumbersStrategy;
    }

    @Override
    public void purchaseLottoTicket(String inputMoney) {
        Purchase purchase = purchaseFrom(inputMoney);
        lottoTicketFrom(purchase);
    }

    private Purchase purchaseFrom(String inputMoney) {
        Purchase purchase = Purchase.from(inputMoney);
        purchaseRepository.save(Purchase.from(inputMoney));
        return purchase;
    }

    private void lottoTicketFrom(Purchase purchase) {
        LottoTicket lottoTicket = LottoTicket.from(purchase.quantity(), pickLottoNumbersStrategy);
        lottoTicketRepository.save(lottoTicket);
    }

    @Override
    public List<PurchasedLottoDto> LottoGames() {
        return lottoTicketRepository.purchasedLottoGames();
    }
}
