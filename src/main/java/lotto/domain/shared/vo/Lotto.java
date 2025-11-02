package lotto.domain.shared.vo;

import java.util.List;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.shared.constant.LottoRules;
import lotto.domain.shared.exception.ErrorType;
import lotto.domain.shared.exception.InvalidLottoNumbersException;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto picks(PickLottoNumbersStrategy picklottoNumbersStrategy) {
        List<Integer> quickPicks = picklottoNumbersStrategy.picks();
        return new Lotto(quickPicks);
    }

    private void validate(List<Integer> numbers) {
        requireValidNumberCount(numbers);
        requireUnique(numbers);
        requireLottoRangeNumbers(numbers);
    }

    private void requireValidNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoRules.LOTTO_BALL_COUNT.value()) {
            throw new InvalidLottoNumbersException(ErrorType.INVALID_NUMBER_COUNT);
        }
    }

    private void requireUnique(List<Integer> numbers) {
        if(hasDuplicates(numbers)) {
            throw new InvalidLottoNumbersException(ErrorType.DUPLICATES_EXIST);
        }
    }

    private boolean hasDuplicates(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    private void requireLottoRangeNumbers(List<Integer> numbers) {
        numbers.forEach(this::requireInLottoRange);
    }

    private void requireInLottoRange(int number) {
        if (!inLottoRange(number)) {
            throw new InvalidLottoNumbersException(ErrorType.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    private boolean inLottoRange(int number) {
        return LottoRules.MIN_NUMBER.value() <= number && number <= LottoRules.MAX_NUMBER.value();
    }

    public List<Integer> numbers() {
        return numbers;
    }
}
