package lotto.application.service.purchase;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.domain.lottoGame.LottoTicketRepository;

public class LottoPurchaseQueryServiceImpl implements LottoPurchaseQueryService {

    private final LottoTicketRepository lottoTicketRepository;

    public LottoPurchaseQueryServiceImpl(LottoTicketRepository lottoTicketRepository) {
        this.lottoTicketRepository = lottoTicketRepository;
    }

    @Override
    public List<PurchasedLottoDto> LottoGames() {
        return lottoTicketRepository.purchasedLottoGames();
    }
}
