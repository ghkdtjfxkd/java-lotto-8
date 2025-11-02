package lotto.infrastructure.repository;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.domain.lottoGame.LottoTicket;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.shared.vo.Lotto;

public class InMemoryLottoTicketRepository implements LottoTicketRepository {

    private LottoTicket lottoTicket;

    // Command
    @Override
    public void save(LottoTicket lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    // Query (view로 향함)
    @Override
    public List<PurchasedLottoDto> purchasedLottoGames() {
        return lottoTicket.purchasedLottoGames().stream()
                .map(lotto -> new PurchasedLottoDto(lotto.numbers()))
                .toList();
    }

    @Override
    public List<Lotto> allLottoGames() {
        return lottoTicket.purchasedLottoGames();
    }
}
