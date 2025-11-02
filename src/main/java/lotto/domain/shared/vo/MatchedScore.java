package lotto.domain.shared.vo;

import lotto.domain.shared.constant.LottoRank;

public record MatchedScore(LottoRank rank, int winningCount) {
}
