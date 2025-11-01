package lotto.controller;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.application.service.LottoPurchaseService;
import lotto.common.BusinessException;
import lotto.io.input.LottoGameInputPort;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.output.LottoGameOutputPort;
import lotto.io.output.dto.PurchasedLottoGamesResponse;

public class LottoController {

    private final LottoGameInputPort inputPort;
    private final LottoGameOutputPort outputPort;

    private final LottoPurchaseService purchaseService;

    public LottoController(LottoGameInputPort inputPort,
                           LottoGameOutputPort outputPort,
                           LottoPurchaseService purchaseService) {
        this.inputPort = inputPort;
        this.outputPort = outputPort;
        this.purchaseService = purchaseService;
    }

    public void run() {
        execute(this::purchaseLotto);
        execute(this::checkingLottoGames);
    }

    private void execute(ExecutableTask task) {
        try {
            task.execute();
            outputPort.printTaskDivider();
        } catch (BusinessException e) {
            outputPort.printError(e);
            execute(task);
        }
    }

    private void purchaseLotto() {
        PurchaseLottoRequest purchaseLottoRequest = inputPort.purchaseMoneyInput();

        purchaseService.purchaseLottoTicket(purchaseLottoRequest.rawMoneyInput());
    }

    private void checkingLottoGames() {
        List<PurchasedLottoDto> lottoGames = purchaseService.LottoGames();
        PurchasedLottoGamesResponse purchasedResponse = PurchasedLottoGamesResponse.from(lottoGames);

        outputPort.print(purchasedResponse);
    }
}
