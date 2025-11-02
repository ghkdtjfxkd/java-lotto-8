package lotto.io.output;

import lotto.application.dto.WinningStatisticDto;
import lotto.io.output.dto.WinningStatisticsResponse;

class WinningStatisticsOutputFormatter {

    private static final String HEADER = "당첨 통계" + System.lineSeparator() + "---";
    private static final String WHITESPACE = " ";

    static String format(WinningStatisticsResponse response) {
        StringBuilder buffer = new StringBuilder();

        buffer.append(HEADER);
        buffer.append(System.lineSeparator());

        for (WinningStatisticDto winningStat : response.winningStatistics()) {
            buffer.append(statusLine(winningStat));
        }

        return buffer.toString();
    }

    private static String statusLine(WinningStatisticDto winningStat) {
        return matchCountFormat(winningStat.matchCount(), winningStat.requireMatchBonus())
                + WHITESPACE

                + moneyFormat(winningStat.prizeMoney())
                + WHITESPACE

                + winningCountFormat(winningStat.winningCount())
                + System.lineSeparator();
    }

    private static String matchCountFormat(int matchCount, boolean bonusBallMatch) {
        if (bonusBallMatch) {
            return java.lang.String.format("%d개 일치, 보너스 볼 일치", matchCount);
        }
        return java.lang.String.format("%d개 일치", matchCount);
    }

    private static String moneyFormat(long prizeMoney) {
        return java.lang.String.format("(%,d원)", prizeMoney);
    }

    private static String winningCountFormat(int winningCount) {
        return String.format("- %d개", winningCount);
    }
}
