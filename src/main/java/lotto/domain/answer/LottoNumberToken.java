package lotto.domain.answer;

import lotto.domain.shared.constant.LottoRules;
import lotto.util.InputChecks;

class LottoNumberToken {

    private final int number;

    private LottoNumberToken(int number) {
        requireInLottoRange(number);
        this.number = number;
    }

    static LottoNumberToken from(String numberInput) {
        requireValid(numberInput);
        return new LottoNumberToken(parseToInt(numberInput));
    }

    private static void requireValid(String numberInput) {
        requireNoWhitespace(numberInput);
        requireNumeric(numberInput);
        requireNoRedundantLeadingZero(numberInput);
    }

    private static void requireNoWhitespace(String numberInput) {
        if (InputChecks.isBlank(numberInput)) {
            throw new InvalidWinningConditionException(ErrorType.WHITE_SPACES_EXIST);
        }
    }

    private static void requireNumeric(String input) {
        if(InputChecks.isNotNumeric(input)) {
            throw new InvalidWinningConditionException(ErrorType.NOT_DIGITS);
        }
    }

    private static void requireNoRedundantLeadingZero(String input) {
        if(InputChecks.hasRedundantLeadingZero(input)) {
            throw new InvalidWinningConditionException(ErrorType.REDUNDANT_LEADING_ZERO);
        }
    }

    private static int parseToInt(String numbersInput) {
        try {
            return Integer.parseInt(numbersInput);
        } catch (NumberFormatException e) {
            throw new InvalidWinningConditionException(ErrorType.OUT_OF_INTEGER);
        }
    }

    private void requireInLottoRange(int number) {
        if (LottoRules.isOutOfRange(number)) {
            throw new InvalidWinningConditionException(ErrorType.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    int number() {
        return number;
    }
}
