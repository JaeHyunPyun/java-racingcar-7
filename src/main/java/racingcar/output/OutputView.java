package racingcar.output;

import java.util.List;
import racingcar.car.Car;

public class OutputView {

    public void printRoundResult(List<Car> racingCars) {
        System.out.println("실행 결과");
        racingCars.forEach(car -> {
            System.out.println(car.getName() + ":" + "-".repeat(Math.max(0, car.getLocation())));
        });
        System.out.println();
    }

    public void printWinners(List<Car> winners) {
        System.out.print("최종 우승자 : ");
        for (int i = 0; i < winners.size(); i++) {
            System.out.print(winners.get(i).getName());
            if (i != winners.size() - 1) {
                System.out.print(",");
            }
        }
    }

}
