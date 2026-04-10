package racingcar;

import java.util.List;

public class OutputView {

    public void printRoundResult(List<Car> racingCars) {
        System.out.println("실행 결과");
        racingCars.forEach(car -> {
            System.out.println(car.getName() + ":" + "-".repeat(Math.max(0, car.getLocation())));
        });
        System.out.println();
    }

}
