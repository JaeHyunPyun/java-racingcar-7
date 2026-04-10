package racingcar;

import java.util.List;
import racingcar.car.Car;
import racingcar.car.RacingParticipantFactory;
import racingcar.car.RacingCars;
import racingcar.input.InputView;
import racingcar.input.UserInput;
import racingcar.output.OutputView;
import racingcar.policy.MovingPolicy;

public class RaceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingParticipantFactory racingParticipantFactory;
    private final MovingPolicy movingPolicy;

    public RaceController(InputView inputView, OutputView outputView,
        RacingParticipantFactory racingParticipantFactory, MovingPolicy movingPolicy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingParticipantFactory = racingParticipantFactory;
        this.movingPolicy = movingPolicy;
    }


    public void run(){
        UserInput userInput = inputView.getUserInput();
        List<Car> racingParticipants = racingParticipantFactory.createRacingParticipants(userInput.carNames());
        RacingCars racingCars = new RacingCars(racingParticipants);
        int raceCount = userInput.attemptCount().count();

        for(int i = 0; i < raceCount; i++){
            racingCars.raceSingleRound(movingPolicy);
            outputView.printRoundResult(racingCars.getRacingCars());
        }

        outputView.printWinners(racingCars.pickWinners());
    }
}
