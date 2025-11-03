package lotto.common;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class TestFixtures {

    public static Stream<Arguments> provideOnlyBlanksInput() {
        return Stream.of(
                Arguments.of("", "빈 입력(EMPTY)"),
                Arguments.of(" ", "공백(BLANK)"),
                Arguments.of("   ", "공백 문자열-[길이: 3]"),
                Arguments.of(" ".repeat(20), "공백 문자열-[길이: 20]")
        );
    }

    public static Stream<Arguments> provideContainedNonDigitsInput() {
        return Stream.of(
                Arguments.of("12 3", "공백(BLANK) 문자 포함"),
                Arguments.of("-123", "(-) 부호 포함"),
                Arguments.of("123.4,5", "숫자가 아닌 문자 포함"),
                Arguments.of("-,.;'ㅏ", "숫자가 아닌 문자로만 이루어진 문자열")
        );
    }

    public static Stream<Arguments> provideOutOfIntegerRangeInputs() {
        return Stream.of(
                Arguments.of("2147483648", "2147483647(Integer.MAX) + 1"),
                Arguments.of("-2147483649", "-2147483648(Integer.MIN) - 1")
        );
    }

    public static Stream<String> provideOutOfLottoRangeInputs() {
        return Stream.of(
                "0",
                "46"
        );
    }

    public static Stream<String> provideRedundantLeadingZeroInputs() {
        return Stream.of(
                "01",
                "0001",
                "001000"
        );
    }
}
