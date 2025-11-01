package lotto;

import lotto.application.service.LottoMatchService;
import lotto.application.service.LottoMatchServiceImpl;
import lotto.application.service.LottoPurchaseService;
import lotto.application.service.LottoPurchaseServiceImpl;
import lotto.controller.LottoController;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.match.WinningConditionRepository;
import lotto.domain.purchase.PurchaseRepository;
import lotto.infrastructure.repository.InMemoryLottoTicketRepository;
import lotto.infrastructure.repository.InMemoryPurchaseRepository;
import lotto.infrastructure.repository.InMemoryWinningConditionRepository;
import lotto.infrastructure.strategy.QuickPicksStrategy;
import lotto.io.input.ConsoleLottoGameInputAdapter;
import lotto.io.input.LottoGameInputPort;
import lotto.io.output.ConsoleLottoGameOutputAdapter;
import lotto.io.output.LottoGameOutputPort;

public class Application {
    public static void main(String[] args) {

        LottoGameInputPort inputPort = new ConsoleLottoGameInputAdapter();
        LottoGameOutputPort outputPort = new ConsoleLottoGameOutputAdapter();

        PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();
        LottoTicketRepository lottoTicketRepository = new InMemoryLottoTicketRepository();
        WinningConditionRepository winningConditionRepository = new InMemoryWinningConditionRepository();

        PickLottoNumbersStrategy pickLottoNumbersStrategy = new QuickPicksStrategy();



        LottoPurchaseService purchaseService = new LottoPurchaseServiceImpl(
                purchaseRepository,
                lottoTicketRepository,
                pickLottoNumbersStrategy
        );

        LottoMatchService lottoMatchService = new LottoMatchServiceImpl(
                winningConditionRepository
        );

        LottoController controller = new LottoController(
                inputPort,
                outputPort,
                purchaseService,
                lottoMatchService
        );

        controller.run();
    }
}
