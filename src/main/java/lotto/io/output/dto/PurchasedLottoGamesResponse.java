package lotto.io.output.dto;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;

public record PurchasedLottoGamesResponse(int quantity,
                                          List<List<Integer>> lottoNumbers) {
    public static PurchasedLottoGamesResponse from(List<PurchasedLottoDto> lottoGames) {
        int quantity = lottoGames.size();

        List<List<Integer>> lottoNumbers = lottoGames.stream()
                .map(PurchasedLottoDto::numbers)
                .toList();

        return new PurchasedLottoGamesResponse(quantity, lottoNumbers);
    }
}
