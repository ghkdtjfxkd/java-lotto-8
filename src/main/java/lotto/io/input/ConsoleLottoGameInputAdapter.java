package lotto.io.input;

import camp.nextstep.edu.missionutils.Console;
import lotto.io.input.dto.BonusNumberRequest;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.input.dto.WinningNumbersRequest;

class ConsoleLottoGameInputAdapter implements LottoGameInputPort {

    @Override
    public PurchaseLottoRequest purchaseMoneyInput() {
        String rawMoneyInput = prompt(InputGuide.PURCHASE_MONEY);
        return new PurchaseLottoRequest(rawMoneyInput);
    }

    @Override
    public WinningNumbersRequest winningNumbersInput() {
        String rawWinningNumbersInput = prompt(InputGuide.WINNING_NUMBERS);
        return new WinningNumbersRequest(rawWinningNumbersInput);
    }

    @Override
    public BonusNumberRequest bonusNumberInput() {
        String rawBonusNumberInput = prompt(InputGuide.BONUS_NUMBER);
        return new BonusNumberRequest(rawBonusNumberInput);
    }

    private String prompt(InputGuide guide) {
        System.out.println(guide.description());
        return Console.readLine();
    }
}
