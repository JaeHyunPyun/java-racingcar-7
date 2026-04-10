package racingcar.input;

import static racingcar.constant.ExceptionMessage.INVALID_ATTEMPT_COUNT_EXCEPTION_MESSAGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttemptCountTest {

    @Test
    @DisplayName("시도횟수가 양수면 정상적으로 AttemptCount가 생성됩니다.")
    void from_PositiveCount_ReturnAttemptCount() {
        //given
        int inputCount = 1;

        //when
        AttemptCount attemptCount = AttemptCount.from(inputCount);

        //then
        Assertions.assertThat(attemptCount.count()).isEqualTo(inputCount);
    }

    @Test
    @DisplayName("시도횟수가 0이면 예외를 반환합니다.")
    void from_ZeroCount_throwException() {
        //given
        int inputCount = 0;

        //when, then
        Assertions.assertThatThrownBy(()-> AttemptCount.from(inputCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_ATTEMPT_COUNT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("시도횟수가 음수면 예외를 반환합니다.")
    void from_NegativeCount_throwException() {
        //given
        int inputCount = -1;

        //when, then
        Assertions.assertThatThrownBy(()-> AttemptCount.from(inputCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_ATTEMPT_COUNT_EXCEPTION_MESSAGE);
    }

}
