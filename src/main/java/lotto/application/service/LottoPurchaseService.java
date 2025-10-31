package lotto.application.service;

import java.util.List;
import lotto.application.dto.PurchasedLotto;

public interface LottoPurchaseService {

    void purchaseLottoTicket(String inputMoney);

    List<PurchasedLotto> LottoGames();
}
