package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    @DisplayName("이름이 5글자 이하인 경우, 자동차 생성에 성공한다.")
    void createCar_NameLengthUnder5_Success(){
        // given
        String name = "test1";

        // when
        Car testCar = new Car(name);

        // then
        Assertions.assertThat(testCar.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("이름이 5글자 초과인 경우, IllegalArgumentException을 던진다.")
    void createCar_NameLengthOver5_throwIllegalArgumentException(){
        // given
        String name = "test12";

        // when
        String exceptionMessage = "자동차 이름은 5자 이하여야 합니다.";

        // then
        Assertions.assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(exceptionMessage);
    }


}
