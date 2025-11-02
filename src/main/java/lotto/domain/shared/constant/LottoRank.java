package lotto.domain.shared.constant;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum LottoRank {

    FIFTH(3, false, 5_000L),
    FOURTH(4, false, 50_000L),
    THIRD(5, false, 1_500_000L),
    SECOND(5, true, 30_000_000L),
    FIRST(6, false, 2_000_000_000L),

    MISS(0, false, 0L);

    private static final int CHECK_BONUS_THRESHOLD = 5;

    private final int matchCount;
    private final boolean requireMatchBonus;
    private final long prizeMoney;

    LottoRank(int matchCount,
              boolean requireMatchBonus,
              long prizeMoney) {
        this.matchCount = matchCount;
        this.requireMatchBonus = requireMatchBonus;
        this.prizeMoney = prizeMoney;
    }

    public static LottoRank from(int matchCount, boolean bonusMatch) {
        if (matchCount == CHECK_BONUS_THRESHOLD) {
            return secondOrThird(bonusMatch);
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount != CHECK_BONUS_THRESHOLD)
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS); //3개 미만은 꽝
    }

    private static LottoRank secondOrThird(boolean bonusMatch) {
        if (bonusMatch) {
            return SECOND;
        }
        return THIRD;
    }

    public static List<LottoRank> getWinningRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank != MISS)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public int matchCount() {
        return matchCount;
    }

    public boolean requireMatchBonus() {
        return requireMatchBonus;
    }

    public long prizeMoney() {
        return prizeMoney;
    }
}
