package lotto.domain.match;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.shared.vo.Lotto;

public class WinningCondition {

    private static final String ANY_LOTTO_RANGE_NUMBERS = "1";
    private static final String DELIMITER = ",";

    private final Lotto winningNumbers;
    private final LottoNumberToken bonus;

    private WinningCondition(Lotto winningNumbers) {
        this.winningNumbers = winningNumbers;
        this.bonus = LottoNumberToken.from(ANY_LOTTO_RANGE_NUMBERS);
    }

    private WinningCondition(Lotto winningNumbers, LottoNumberToken bonus) {
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    public static WinningCondition from(String winningNumbersInput) {
        requireNonBlank(winningNumbersInput);
        Lotto winning = new Lotto(parseToNumberTokens(streamOf(winningNumbersInput)));
        return new WinningCondition(winning);
    }


    private static void requireNonBlank(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.isBlank()) {
            throw new InvalidMatchConditionException(ErrorType.BLANK);
        }
    }

    private static List<Integer> parseToNumberTokens(Stream<LottoNumberToken> numbersInputStream) {
        return numbersInputStream.map(LottoNumberToken::value)
                .toList();
    }

    private static Stream<LottoNumberToken> streamOf(String winningNumbersInput) {
        return Arrays.stream(winningNumbersInput.split(DELIMITER))
                .peek(WinningCondition::requireNumeric)
                .map(LottoNumberToken::from);
    }

    private static void requireNumeric(String input) {
        for (char token : input.toCharArray()) {
            requireDigit(token);
        }
    }

    private static void requireDigit(char token) {
        if (!Character.isDigit(token)) {
            throw new InvalidMatchConditionException(ErrorType.NOT_DIGITS_OR_DELIMITER);
        }
    }

    public WinningCondition withBonus(String bonusNumbersInput) {
        requireNonBlank(bonusNumbersInput);
        requireUnique(bonusNumbersInput);
        return new WinningCondition(this.winningNumbers, LottoNumberToken.from(bonusNumbersInput));
    }

    private void requireUnique(String bonusNumbersInput) {
        LottoNumberToken number = LottoNumberToken.from(bonusNumbersInput);
        if(winningNumbers.isContained(number.value())) {
            throw new InvalidMatchConditionException(ErrorType.DUPLICATES_EXIST);
        }
    }

    public int matchedCount(Lotto lottoGame) {
        return winningNumbers.matchedCount(lottoGame.numbers());
    }

    public boolean bonusIsMatched(Lotto lottoGame) {
        return lottoGame.isContained(bonus.value());
    }
}
