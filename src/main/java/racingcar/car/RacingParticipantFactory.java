package racingcar.car;

import java.util.List;
import racingcar.input.CarNames;

public class RacingParticipantFactory {

    public List<Car> createRacingParticipants(CarNames carNames){
        return carNames.names().stream()
            .map(Car::new)
            .toList();
    }

}
