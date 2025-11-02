package lotto.domain.result;

import java.util.List;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;
import lotto.domain.shared.vo.MatchedScore;

public class LottoResult {

    private final ScoreBoard scoreBoard;

    private LottoResult(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public static LottoResult from(List<Lotto> lottoGames, LottoAnswer lottoAnswer) {
        return new LottoResult(ScoreBoard.from(lottoGames, lottoAnswer));
    }

    public List<MatchedScore> scores() {
        return scoreBoard.matchedScores().entrySet()
                .stream()
                .map(entry -> new MatchedScore(entry.getKey(), entry.getValue()))
                .toList();
    }
}
