package lotto.controller;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.application.service.LottoMatchService;
import lotto.application.service.LottoPurchaseService;
import lotto.common.BusinessException;
import lotto.io.input.LottoGameInputPort;
import lotto.io.input.dto.BonusNumberRequest;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.input.dto.WinningNumbersRequest;
import lotto.io.output.LottoGameOutputPort;
import lotto.io.output.dto.PurchasedLottoGamesResponse;

public class LottoController {

    private final LottoGameInputPort inputPort;
    private final LottoGameOutputPort outputPort;

    private final LottoPurchaseService purchaseService;
    private final LottoMatchService matchService;

    public LottoController(LottoGameInputPort inputPort,
                           LottoGameOutputPort outputPort,
                           LottoPurchaseService purchaseService,
                           LottoMatchService matchService) {
        this.inputPort = inputPort;
        this.outputPort = outputPort;
        this.purchaseService = purchaseService;
        this.matchService = matchService;
    }

    public void run() {
        execute(this::purchaseLotto);
        execute(this::checkingLottoGames);
        execute(this::registerWinningNumbers);
        execute(this::registerBonusNumber);
    }

    private void execute(ExecutableTask task) {
        while (true) {
            try {
                task.execute();
                outputPort.printTaskDivider();
                return;
            } catch (BusinessException e) {
                outputPort.printTaskDivider();
                outputPort.printError(e);
            }
        }
    }

    private void purchaseLotto() {
        PurchaseLottoRequest request = inputPort.purchaseMoneyInput();
        purchaseService.purchaseLottoTicket(request.rawMoneyInput());
    }

    private void checkingLottoGames() {
        List<PurchasedLottoDto> lottoGames = purchaseService.LottoGames();
        PurchasedLottoGamesResponse purchasedResponse = PurchasedLottoGamesResponse.from(lottoGames);

        outputPort.print(purchasedResponse);
    }

    private void registerWinningNumbers() {
        WinningNumbersRequest request = inputPort.winningNumbersInput();
        matchService.registerWinningNumbers(request.rawWinningNumbersInput());
    }

    private void registerBonusNumber() {
        BonusNumberRequest request = inputPort.bonusNumberInput();
        matchService.registerBonusNumber(request.rawBonusNumberInput());
    }
}
