package lotto.domain.answer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;
import lotto.util.InputChecks;

public class WinningCondition {

    private static final int PRESERVE_EMPTY_TOKENS = -1;
    private static final String ANY_LOTTO_RANGE_NUMBERS = "1"; // 1 ~ 45 사이의 숫자 문자열 아무거나
    private static final String DELIMITER = ",";

    private final Lotto winningLotto;
    private final LottoNumberToken bonus;

    private WinningCondition(Lotto winningLotto) {
        this.winningLotto = winningLotto;
        this.bonus = LottoNumberToken.from(ANY_LOTTO_RANGE_NUMBERS);
    }

    private WinningCondition(Lotto winningLotto, LottoNumberToken bonus) {
        this.winningLotto = winningLotto;
        this.bonus = bonus;
    }

    public static WinningCondition from(String winningNumbersInput) {
        requireNonBlank(winningNumbersInput);
        Lotto winning = new Lotto(parseToNumberTokens(streamOf(winningNumbersInput)));
        return new WinningCondition(winning);
    }

    private static void requireNonBlank(String winningNumbersInput) {
        if (InputChecks.isBlank(winningNumbersInput)) {
            throw new InvalidWinningConditionException(ErrorType.BLANK);
        }
    }

    private static List<Integer> parseToNumberTokens(Stream<LottoNumberToken> numbersInputStream) {
        return numbersInputStream.map(LottoNumberToken::number)
                .toList();
    }

    private static Stream<LottoNumberToken> streamOf(String winningNumbersInput) {
        return Arrays.stream(winningNumbersInput.split(DELIMITER, PRESERVE_EMPTY_TOKENS))
                .peek(WinningCondition::requireNumeric)
                .map(LottoNumberToken::from);
    }

    private static void requireNumeric(String input) {
        if(InputChecks.isNotNumeric(input)) {
            throw new InvalidWinningConditionException(ErrorType.NOT_DIGITS_OR_DELIMITER);
        }
    }

    public WinningCondition withBonus(String bonusNumbersInput) {
        requireNonBlank(bonusNumbersInput);
        requireUnique(bonusNumbersInput);
        return new WinningCondition(this.winningLotto, LottoNumberToken.from(bonusNumbersInput));
    }

    private void requireUnique(String bonusNumbersInput) {
        LottoNumberToken bonus = LottoNumberToken.from(bonusNumbersInput);
        if (winningLotto.numbers().contains(bonus.number())) {
            throw new InvalidWinningConditionException(ErrorType.DUPLICATES_EXIST);
        }
    }

    public LottoAnswer createAnswer() {
        return LottoAnswer.of(winningLotto, bonus.number());
    }
}
