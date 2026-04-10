package racingcar.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.policy.MovingPolicy;

public class RacingCars {

    private final List<Car> racingCars;

    public RacingCars(List<Car> racingCars) {
        this.racingCars = new ArrayList<>(racingCars);
    }

    public void raceSingleRound(MovingPolicy movingPolicy){
        racingCars.forEach(car -> car.move(movingPolicy));
    }

    public List<Car> getRacingCars() {
        return Collections.unmodifiableList(racingCars);
    }

    public List<Car> pickWinners(){
        Car winnerBase = racingCars.stream()
            .max(Car::compareLocation)
            .orElseThrow(() -> new IllegalArgumentException("참가 자동차가 존재하지 않습니다."));

        return getTieWinners(winnerBase);
    }

    private List<Car> getTieWinners(Car winnerBase) {
        List<Car> winners = new ArrayList<>();
        racingCars.forEach(
            car -> {
                if(winnerBase.compareLocation(car) == 0){
                    winners.add(car);
                }
            }
        );
        return winners;
    }

}
