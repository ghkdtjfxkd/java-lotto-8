package lotto.domain.match;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.shared.vo.Lotto;

public class WinningCondition {

    private static short BONUS_CHECK_MATCH_COUNT = 5;
    private static final String DELIMITER = ",";

    private final Lotto winningNumbers;
    private final BonusNumber bonus;

    private WinningCondition(Lotto winningNumbers) {
        this.winningNumbers = winningNumbers;
        this.bonus = new BonusNumber();
    }

    public static WinningCondition of(String winningNumbersInput) {
        requireNonBlank(winningNumbersInput);
        Lotto winning = new Lotto(parseToNumberTokens(streamOf(winningNumbersInput)));
        return new WinningCondition(winning);
    }

    private static void requireNonBlank(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.isBlank()) {
            throw new InvalidMatchConditionException(ErrorType.BLANK);
        }
    }

    private static List<Integer> parseToNumberTokens(Stream<NumberToken> numbersInputStream) {
        return numbersInputStream.map(NumberToken::value)
                .toList();
    }

    private static Stream<NumberToken> streamOf(String winningNumbersInput) {
        return Arrays.stream(winningNumbersInput.split(DELIMITER))
                .map(NumberToken::from);
    }

    int matchedCount(Lotto lottoGame) {
        return winningNumbers.matchedCount(lottoGame.numbers());
    }
}
