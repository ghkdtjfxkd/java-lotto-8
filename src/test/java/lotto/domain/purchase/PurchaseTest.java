package lotto.domain.purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import lotto.common.TestFixtures;
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
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidPurchaseException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideOnlyBlanksInput")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 정상 출력 테스트")
    void blank_input_exception_test(String input, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Purchase.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.BLANK.description());
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideContainedNonDigitsInput")
    @DisplayName("숫자가 아닌 문자가 포함된 예외 메지시 정상 출력 테스트")
    void empty_input_exception_test(String input, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Purchase.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.NOT_DIGIT.description());
    }

    @DisplayName("구매 금액에 불필요한 0으로 시작하는 숫자 입력 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 숫자 입력: [{0}]")
    @MethodSource("provideRedundantLeadingZeroInputs")
    void leading_zero_exception_message_test(String input) {
        assertThatThrownBy(() -> Purchase.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.REDUNDANT_LEADING_ZERO.description());
    }

    private static Stream<Arguments> provideOnlyBlanksInput() {
        return TestFixtures.provideOnlyBlanksInput();
    }

    private static Stream<Arguments> provideContainedNonDigitsInput() {
        return TestFixtures.provideContainedNonDigitsInput();
    }

    private static Stream<String> provideRedundantLeadingZeroInputs() {
        return TestFixtures.provideRedundantLeadingZeroInputs();
    }
}
