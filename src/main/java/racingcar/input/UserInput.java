package racingcar.input;

public record UserInput(
    CarNames carNames,
    AttemptCount attemptCount
) {
    public static UserInput from(CarNames carNames, AttemptCount count) {
        return new UserInput(carNames, count);
    }

}
