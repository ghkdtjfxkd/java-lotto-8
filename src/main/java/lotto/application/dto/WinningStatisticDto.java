package lotto.application.dto;

import lotto.domain.shared.constant.LottoRank;

public record WinningStatisticDto(int matchCount,
                                  boolean requireMatchBonus,
                                  long prizeMoney,
                                  int winningCount) {

    public static WinningStatisticDto from(LottoRank rank, int winningCount) {
        return new WinningStatisticDto(
                rank.matchCount(),
                rank.requireMatchBonus(),
                rank.prizeMoney(),
                winningCount
        );
    }
}
