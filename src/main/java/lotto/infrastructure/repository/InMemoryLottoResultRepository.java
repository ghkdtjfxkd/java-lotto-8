package lotto.infrastructure.repository;

import java.util.List;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoResultRepository;
import lotto.domain.shared.vo.MatchedScore;

public class InMemoryLottoResultRepository implements LottoResultRepository {

    private LottoResult lottoResults;

    @Override
    public void save(LottoResult lottoResult) {
        this.lottoResults = lottoResult;
    }

    @Override
    public List<MatchedScore> getMatchedScores() {
        return lottoResults.scores();
    }
}
