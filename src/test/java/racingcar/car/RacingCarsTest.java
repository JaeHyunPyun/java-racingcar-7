package racingcar.car;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.policy.MovingPolicy;

public class RacingCarsTest {

    @Nested
    @DisplayName("참여 경주 자동차가 존재하는 경우")
    class ContextWithCars {

        private RacingCars racingCars;

        @BeforeEach
        void setUp() {
            List<Car> testCars = new ArrayList<>();
            testCars.add(new Car("test1"));
            testCars.add(new Car("test2"));
            testCars.add(new Car("test3"));

            racingCars = new RacingCars(testCars);
        }

        @Test
        @DisplayName("전진 가능한 Moving Policy에서 경주를 실행하면 각 차량이 전진한다.")
        void raceSingleRound_MovableCondition_AllCarLocationIncrease() {
            //given
            MovingPolicy alwaysMove = () -> true;

            //when
            racingCars.raceSingleRound(alwaysMove);

            //then
            Assertions.assertThat(racingCars.getRacingCars())
                .extracting(Car::getLocation)
                .containsExactly(1, 1, 1);
        }

        @Test
        @DisplayName("전진 불가능한 Moving Policy에서 경주를 실행하면 각 차량이 움직이지 않는다.")
        void raceSingleRound_NotMovableCondition_AllCarLocationMaintain() {
            //given
            MovingPolicy neverMove = () -> false;

            //when
            racingCars.raceSingleRound(neverMove);

            //then
            Assertions.assertThat(racingCars.getRacingCars())
                .extracting(Car::getLocation)
                .containsExactly(0, 0, 0);
        }

        @Test
        @DisplayName("우승 자동차가 1개일때, 우승 자동차 1개를 담은 리스트를 반환한다.")
        void pickWinners_SingleWinner_ReturnSingleElementList(){
            // given
            MovingPolicy alwaysMove = ()->true;
            MovingPolicy neverMove = ()->false;

            racingCars.getRacingCars().forEach(car -> {
                if (car.getName().equals("test1")) {
                    car.move(alwaysMove);
                } else {
                    car.move(neverMove);
                }
            });

            // when
            List<Car> winners = racingCars.pickWinners();

            // then
            Assertions.assertThat(winners.size()).isEqualTo(1);
            Assertions.assertThat(winners.get(0).getName()).isEqualTo("test1");
        }

        @Test
        @DisplayName("우승 자동차가 여러대일때, 동점인 우승 자동차 목록을 담은 리스트를 반환한다.")
        void pickWinners_MultipleWinners_ReturnMultipleElementList(){
            // given
            MovingPolicy alwaysMove = ()->true;
            MovingPolicy neverMove = ()->false;

            racingCars.getRacingCars().forEach(car -> {
                if (car.getName().equals("test3")) {
                    car.move(neverMove);
                } else {
                    car.move(alwaysMove);
                }
            });

            // when
            List<Car> winners = racingCars.pickWinners();

            // then
            Assertions.assertThat(winners.size()).isEqualTo(2);
            Assertions.assertThat(winners).extracting(Car::getName)
                    .containsExactly("test1", "test2");
        }
    }

    @Nested
    @DisplayName("참여 경주 자동차가 존재하지 않는 경우")
    class ContextWithoutCars {

        private RacingCars racingCars;

        @BeforeEach
        void setUp(){
            racingCars = new RacingCars(new ArrayList<>());
        }

        @Test
        @DisplayName("참가 자동차 목록이 비어있을때, raceSingleRound는 아무런 동작을 하지 않는다.")
        void raceSingleRound_EmptyRacingCars_NoAction(){
            // given
            MovingPolicy alwaysMove = ()->true;

            // when, then
            Assertions.assertThatCode(() -> racingCars.raceSingleRound(alwaysMove))
                .doesNotThrowAnyException();

            Assertions.assertThat(racingCars.getRacingCars())
                .isEmpty();
        }

        @Test
        @DisplayName("참가 자동차 목록이 비어있을때, pickWinners는 예외를 던진다.")
        void pickWinners_EmptyRacingCars_ThrowException(){
            // when, then
            Assertions.assertThatThrownBy(()->racingCars.pickWinners())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가 자동차가 존재하지 않습니다.");
        }
    }
}
