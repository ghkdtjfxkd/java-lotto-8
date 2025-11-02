package lotto;

import lotto.application.service.LottoAnswerService;
import lotto.application.service.LottoAnswerServiceImpl;
import lotto.application.service.LottoPurchaseService;
import lotto.application.service.LottoPurchaseServiceImpl;
import lotto.application.service.LottoResultService;
import lotto.application.service.LottoResultServiceImpl;
import lotto.controller.LottoController;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.purchase.PurchaseRepository;
import lotto.infrastructure.repository.InMemoryLottoTicketRepository;
import lotto.infrastructure.repository.InMemoryPurchaseRepository;
import lotto.infrastructure.repository.InMemoryLottoAnswerRepository;
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
        LottoAnswerRepository lottoAnswerRepository = new InMemoryLottoAnswerRepository();

        PickLottoNumbersStrategy pickLottoNumbersStrategy = new QuickPicksStrategy();



        LottoPurchaseService purchaseService = new LottoPurchaseServiceImpl(
                purchaseRepository,
                lottoTicketRepository,
                pickLottoNumbersStrategy
        );

        LottoAnswerService lottoAnswerService = new LottoAnswerServiceImpl(
                lottoAnswerRepository
        );

        LottoResultService lottoResultService = new LottoResultServiceImpl(
                purchaseRepository,
                lottoTicketRepository,
                lottoAnswerRepository
        );

        LottoController controller = new LottoController(
                inputPort,
                outputPort,
                purchaseService,
                lottoAnswerService,
                lottoResultService
        );

        controller.run();
    }
}
