package lotto.domain.purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PurchaseTest {

    @DisplayName("입력이 `null`일 때, InvalidPurchaseException 예외 발생 테스트")
    @Test
    void null_throws_InvalidPurchaseException_test() {
        Exception exception = assertThrows(InvalidPurchaseException.class,
                () -> Purchase.from(null));

        assertThat(exception)
                .isInstanceOf(InvalidPurchaseException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideOnlyBlanksInput")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 정상 출력 테스트")
    void blank_input_exception_test(String input, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Purchase.from(input))
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.BLANK.description());
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideContainedNonDigitsInput")
    @DisplayName("숫자가 아닌 문자가 포함된 예외 메지시 정상 출력 테스트")
    void empty_input_exception_test(String input, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Purchase.from(input))
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.NOT_DIGIT.description());
    }

    private static Stream<Arguments> provideOnlyBlanksInput() {
        return Stream.of(
                Arguments.of("", "빈 입력(EMPTY)"),
                Arguments.of(" ", "공백(BLANK)"),
                Arguments.of("   ", "공백 문자열-[길이: 3]"),
                Arguments.of(" ".repeat(20), "공백 문자열-[길이: 20]")
        );
    }

    private static Stream<Arguments> provideContainedNonDigitsInput() {
        return Stream.of(
                Arguments.of("12 3", "공백(BLANK) 문자 포함"),
                Arguments.of("-123", "(-) 부호 포함"),
                Arguments.of("123.4,5", "숫자가 아닌 문자 포함"),
                Arguments.of("-,.;'ㅏ", "숫자가 아닌 문자로만 이루어진 문자열")
        );
    }
}
