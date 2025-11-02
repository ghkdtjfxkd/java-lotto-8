package lotto.domain.lottoGame;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.domain.shared.vo.Lotto;

public interface LottoTicketRepository {

    void save(LottoTicket lottoTicket);

    List<PurchasedLottoDto> purchasedLottoGames();

    List<Lotto> allLottoGames();
}
