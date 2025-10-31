package lotto.domain.shared.vo;

import java.util.Collection;
import java.util.List;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.shared.constant.LottoRules;

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
    }

    private void requireValidNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoRules.LOTTO_BALL_COUNT.value()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void requireUnique(List<Integer> numbers) {
        if(hasDuplicates(numbers)) {
            throw new IllegalArgumentException("[Error]유니크해야해");
        }
    }

    private boolean hasDuplicates(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    public List<Integer> numbers() {
        return numbers;
    }

    public int matchedCount(Collection<Integer> winningSet) {
        return (int) this.numbers.stream()
                .filter(winningSet::contains)
                .count();
    }

    public boolean isContained(int number) {
        return this.numbers.contains(number);
    }
}
