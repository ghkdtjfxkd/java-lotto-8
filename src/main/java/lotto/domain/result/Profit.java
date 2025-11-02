package lotto.domain.result;

import java.util.List;
import lotto.domain.shared.vo.MatchedScore;

public class Profit {

    private final long prizeAmount;

    private Profit(long prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public static Profit from(List<MatchedScore> scores) {
        return new Profit(calculatePrize(scores));
    }

    private static long calculatePrize(List<MatchedScore> scores) {
        return scores.stream()
                .mapToLong(matchedScore ->
                        matchedScore.rank().prizeMoney() * matchedScore.winningCount())
                .sum();
    }


    public double rateOf(int purchaseAmount) {
        if(purchaseAmount == 0) {
            throw new IllegalArgumentException("[ERROR]: 0으로는 나눌 수 없습니다.");
        }

        return (double) prizeAmount / (double) purchaseAmount * 100;
    }
}
