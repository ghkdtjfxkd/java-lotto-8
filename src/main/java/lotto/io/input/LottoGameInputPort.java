package lotto.io.input;

import lotto.io.input.dto.BonusNumberRequest;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.input.dto.WinningNumbersRequest;

public interface LottoGameInputPort {

    PurchaseLottoRequest purchaseMoneyInput();

    WinningNumbersRequest winningNumbersInput();

    BonusNumberRequest bonusNumberInput();
}
