package lotto.domain.answer;

import lotto.domain.shared.constant.LottoRules;

class LottoNumberToken {

    private final int number;

    private LottoNumberToken(int number) {
        requireInLottoRange(number);
        this.number = number;
    }

    static LottoNumberToken from(String numberInput) {
        requireNoWhitespace(numberInput);
        requireNumeric(numberInput);
        return new LottoNumberToken(parseToInt(numberInput));
    }

    private static void requireNoWhitespace(String numberInput) {
        if (numberInput.isBlank()) {
            throw new InvalidMatchConditionException(ErrorType.WHITE_SPACES_EXIST);
        }
    }

    private static void requireNumeric(String input) {
        for (char token : input.toCharArray()) {
            requireDigit(token);
        }
    }

    private static void requireDigit(char token) {
        if (!Character.isDigit(token)) {
            throw new InvalidMatchConditionException(ErrorType.NOT_DIGITS);
        }
    }

    private static int parseToInt(String numbersInput) {
        try {
            return Integer.parseInt(numbersInput);
        } catch (NumberFormatException e) {
            throw new InvalidMatchConditionException(ErrorType.OUT_OF_INTEGER);
        }
    }

    private void requireInLottoRange(int number) {
        if (!inLottoRange(number)) {
            throw new InvalidMatchConditionException(ErrorType.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    private boolean inLottoRange(int number) {
        return LottoRules.MIN_NUMBER.value() <= number && number <= LottoRules.MAX_NUMBER.value();
    }

    int number() {
        return number;
    }
}
