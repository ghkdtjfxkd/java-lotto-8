package lotto.domain.shared.vo;

import java.util.HashSet;
import java.util.Set;

public class LottoAnswer {

    private final Set<Integer> winningNumbers;
    private final int bonusNumber;

    private LottoAnswer(Set<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoAnswer of(Lotto winningLotto, int bonus) {
        Set<Integer> winningNumbers = new HashSet<>(winningLotto.numbers());
        return new LottoAnswer(winningNumbers, bonus);
    }

    public int matchedCount(Lotto lotto) {
        return (int) lotto.numbers().stream().
                filter(winningNumbers::contains)
                .count();
    }

    public boolean bonusNumberMatches(Lotto lotto) {
        return lotto.numbers().contains(bonusNumber);
    }
}
