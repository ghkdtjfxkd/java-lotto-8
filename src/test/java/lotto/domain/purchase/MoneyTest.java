package lotto.domain.purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("구매 금액 테스트")
class MoneyTest {

    @DisplayName("범위를 벗어난 금액 입력 시 예외 발생 테스트")
    @ParameterizedTest(name = "[{index}] 금액: [{0}]")
    @MethodSource({
            "provideNonPositiveMoneyInputs",
            "provideOutOfIntegerRangeMoneyInputs"})
    void wrong_purchase_money_exception_test(String input) {
        assertThrows(InvalidPurchaseException.class, () -> Money.from(input));
    }

    @DisplayName("int 범위를 넘어가는 금액 입력 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 금액: [{0}] ({1})")
    @MethodSource("provideOutOfIntegerRangeMoneyInputs")
    void out_of_range_purchase_money_exception_message_test(String input, String description) {
        assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.OUT_OF_INTEGER.description());
    }

    @DisplayName("0보다 작은 금액 입력 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 금액: [{0}]")
    @MethodSource("provideNonPositiveMoneyInputs")
    void non_positive_money_exception_message_test(String input) {
        assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.NOT_POSITIVE.description());
    }

    private static Stream<Arguments> provideOutOfIntegerRangeMoneyInputs() {
        return Stream.of(
                Arguments.of("2147483648", "2147483647(Integer.MAX) + 1"),
                Arguments.of("-2147483649", "-2147483648(Integer.MIN) - 1")
        );
    }

    private static Stream<String> provideNonPositiveMoneyInputs() {
        return Stream.of(
                "0",
                "-1"
        );
    }
}
