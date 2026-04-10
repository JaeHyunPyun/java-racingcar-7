package racingcar;

import racingcar.car.RacingParticipantFactory;
import racingcar.input.InputView;
import racingcar.number.RandomNumberGenerator;
import racingcar.output.OutputView;
import racingcar.policy.RandomMovingPolicy;

public class Application {

    public static void main(String[] args) {
        RaceController raceController = new RaceController(
            new InputView(),
            new OutputView(),
            new RacingParticipantFactory(),
            new RandomMovingPolicy(new RandomNumberGenerator())
        );
        raceController.run();
    }
}
