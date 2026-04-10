package racingcar.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.policy.MovingPolicy;
import racingcar.policy.RandomMovingPolicy;

public class CarTest {

    @Test
    @DisplayName("이름이 5글자 이하인 경우, 자동차 생성에 성공한다.")
    void createCar_NameLengthUnder5_Success() {
        // given
        String name = "test1";

        // when
        Car testCar = new Car(name);

        // then
        Assertions.assertThat(testCar.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("이름이 5글자 초과인 경우, IllegalArgumentException을 던진다.")
    void createCar_NameLengthOver5_throwIllegalArgumentException() {
        // given
        String name = "test12";

        // when
        String exceptionMessage = "자동차 이름은 5자 이하여야 합니다.";

        // then
        Assertions.assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(exceptionMessage);
    }

    @Test
    @DisplayName("전진 가능 상태일때, 자동차가 전진한다.")
    void move_MovableCondition_IncreaseLocation() {
        //given
        Car car = new Car("test1");
        MovingPolicy movableMovingPolicy = new RandomMovingPolicy(() -> 4);
        int defaultLocation = car.getLocation();

        //when
        car.move(movableMovingPolicy);

        //then
        Assertions.assertThat(car.getLocation()).isEqualTo(defaultLocation + 1);
    }

    @Test
    @DisplayName("전진 불가능 상태일때, 자동차가 전진하지 않는다.")
    void move_NotMovableCondition_MaintainLocation() {
        //given
        Car car = new Car("test1");
        MovingPolicy notMovableMovingPolicy = new RandomMovingPolicy(() -> 3);
        int defaultLocation = car.getLocation();

        //when
        car.move(notMovableMovingPolicy);

        //then
        Assertions.assertThat(car.getLocation()).isEqualTo(defaultLocation);
    }


}
