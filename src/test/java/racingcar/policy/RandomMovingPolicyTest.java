package racingcar.policy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMovingPolicyTest {

    @Test
    @DisplayName("숫자 4 이상인 경우, true를 반환한다.")
    void isMovable_numberOver4_true(){
        // given
        RandomMovingPolicy randomMovingPolicy = new RandomMovingPolicy(() -> 4);

        // when, then
        Assertions.assertThat(randomMovingPolicy.isMovable()).isTrue();
    }

    @Test
    @DisplayName("숫자 4 미만인 경우, false를 반환한다.")
    void isMovable_numberUnder4_false(){
        // given
        RandomMovingPolicy randomMovingPolicy = new RandomMovingPolicy(() -> 3);

        // when, then
        Assertions.assertThat(randomMovingPolicy.isMovable()).isFalse();
    }

}