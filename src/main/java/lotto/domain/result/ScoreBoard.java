package lotto.domain.result;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.shared.constant.LottoRank;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;

class ScoreBoard {

    private final Map<LottoRank, Integer> matchedScores;

    private ScoreBoard(Map<LottoRank, Integer> matchedScores) {
        this.matchedScores = matchedScores;
    }

    static ScoreBoard from(List<Lotto> unclassified, LottoAnswer answer) {
        Map<LottoRank, Integer> matchedScores = new EnumMap<>(LottoRank.class);

        unclassified.forEach(lotto ->
                matchedScores.merge(rank(lotto, answer), 1, Integer::sum));

        return new ScoreBoard(matchedScores);
    }

    private static LottoRank rank(Lotto lotto, LottoAnswer answer) {
        return LottoRank.from(
                answer.matchedCount(lotto),
                answer.bonusNumberMatches(lotto));
    }

    Map<LottoRank, Integer> matchedScores() {
        Map<LottoRank, Integer> completeScores = new EnumMap<>(LottoRank.class);
        
        LottoRank.getWinningRanks()
                .forEach(winningRank -> putScore(winningRank, completeScores));

        return Collections.unmodifiableMap(completeScores);
    }

    private void putScore(LottoRank winningRank, Map<LottoRank, Integer> completeScores) {
        completeScores.put(winningRank, matchedScores.getOrDefault(winningRank, 0));
    }
}
