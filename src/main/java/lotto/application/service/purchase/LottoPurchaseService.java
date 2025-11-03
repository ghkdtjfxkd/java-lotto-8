package lotto.application.service.purchase;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;

public interface LottoPurchaseService {

    void purchaseLottoTicket(String inputMoney);

    List<PurchasedLottoDto> LottoGames();
}
