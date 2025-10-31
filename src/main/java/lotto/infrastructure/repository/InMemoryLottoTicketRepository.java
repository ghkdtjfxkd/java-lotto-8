package lotto.infrastructure.repository;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.domain.lottoGame.LottoTicket;
import lotto.domain.lottoGame.LottoTicketRepository;

public class InMemoryLottoTicketRepository implements LottoTicketRepository {

    private LottoTicket lottoTicket;

    // Command
    @Override
    public void save(LottoTicket lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    // Query
    @Override
    public List<PurchasedLottoDto> purchasedLottoGames() {
        return lottoTicket.purchasedLottoGames().stream()
                .map(lotto -> new PurchasedLottoDto(lotto.numbers()))
                .toList();
    }
}
