package lotto.domain.result;

import java.util.List;
import lotto.domain.shared.vo.MatchedScore;

public interface LottoResultRepository {

    void save(LottoResult lottoResult);

    List<MatchedScore> getMatchedScores();
}
