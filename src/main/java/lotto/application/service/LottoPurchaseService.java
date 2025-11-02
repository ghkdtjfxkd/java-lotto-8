package lotto.application.service;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;

public interface LottoPurchaseService {

    void purchaseLottoTicket(String inputMoney);

    List<PurchasedLottoDto> LottoGames();
}
