package lotto.config;

import lotto.application.service.answer.LottoAnswerService;
import lotto.application.service.answer.LottoAnswerServiceImpl;
import lotto.application.service.purchase.LottoPurchaseService;
import lotto.application.service.purchase.LottoPurchaseServiceImpl;
import lotto.application.service.result.LottoResultQueryService;
import lotto.application.service.result.LottoResultQueryServiceImpl;
import lotto.application.service.result.LottoResultCommandService;
import lotto.application.service.result.LottoResultCommandServiceImpl;
import lotto.controller.LottoController;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.purchase.PurchaseRepository;
import lotto.domain.result.LottoResultRepository;
import lotto.infrastructure.repository.InMemoryLottoAnswerRepository;
import lotto.infrastructure.repository.InMemoryLottoResultRepository;
import lotto.infrastructure.repository.InMemoryLottoTicketRepository;
import lotto.infrastructure.repository.InMemoryPurchaseRepository;
import lotto.infrastructure.strategy.QuickPicksStrategy;
import lotto.io.input.ConsoleLottoGameInputAdapter;
import lotto.io.input.LottoGameInputPort;
import lotto.io.output.ConsoleLottoGameOutputAdapter;
import lotto.io.output.LottoGameOutputPort;

public class LottoConfig {

    public static LottoController setup() {
        PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();
        LottoTicketRepository lottoTicketRepository = new InMemoryLottoTicketRepository();
        LottoAnswerRepository lottoAnswerRepository = new InMemoryLottoAnswerRepository();
        LottoResultRepository lottoResultRepository = new InMemoryLottoResultRepository();

        return new LottoController(
                createInputPort(),
                createOutputPort(),
                createPurchaseService(purchaseRepository, lottoTicketRepository),
                createAnswerService(lottoAnswerRepository),
                createResultService(lottoTicketRepository, lottoAnswerRepository, lottoResultRepository),
                createResultQueryService(purchaseRepository, lottoResultRepository)
        );
    }

    private static LottoGameInputPort createInputPort() {
        return new ConsoleLottoGameInputAdapter();
    }

    private static LottoGameOutputPort createOutputPort() {
        return new ConsoleLottoGameOutputAdapter();
    }

    private static PickLottoNumbersStrategy createPickStrategy() {
        return new QuickPicksStrategy();
    }

    private static LottoPurchaseService createPurchaseService(
            PurchaseRepository purchaseRepository,
            LottoTicketRepository lottoTicketRepository) {

        return new LottoPurchaseServiceImpl(
                purchaseRepository,
                lottoTicketRepository,
                createPickStrategy()
        );
    }

    private static LottoAnswerService createAnswerService(
            LottoAnswerRepository lottoAnswerRepository) {

        return new LottoAnswerServiceImpl(lottoAnswerRepository);
    }

    private static LottoResultCommandService createResultService(
            LottoTicketRepository lottoTicketRepository,
            LottoAnswerRepository lottoAnswerRepository,
            LottoResultRepository lottoResultRepository) {

        return new LottoResultCommandServiceImpl(
                lottoTicketRepository,
                lottoAnswerRepository,
                lottoResultRepository
        );
    }

    private static LottoResultQueryService createResultQueryService(
            PurchaseRepository purchaseRepository,
            LottoResultRepository lottoResultRepository) {

        return new LottoResultQueryServiceImpl(
                purchaseRepository,
                lottoResultRepository
        );
    }
}
