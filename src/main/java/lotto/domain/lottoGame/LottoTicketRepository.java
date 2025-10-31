package lotto.domain.lottoGame;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;

public interface LottoTicketRepository {

    void save(LottoTicket lottoTicket);

    List<PurchasedLottoDto> purchasedLottoGames();
}
