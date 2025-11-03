package lotto.application.service.answer;

public interface LottoAnswerCommandService {

    void registerWinningNumbers(String winningNumbersInput);

    void registerBonusNumber(String bonusNumberInput);
}
