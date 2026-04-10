package racingcar.input;

import static racingcar.constant.ExceptionMessage.INVALID_ATTEMPT_COUNT_EXCEPTION_MESSAGE;

public record AttemptCount(
    int count
) {
    public static AttemptCount from(int input) {
        validateCount(input);
        return new AttemptCount(input);
    }

    private static void validateCount(int input){
        if(input <= 0){
            throw new IllegalArgumentException(INVALID_ATTEMPT_COUNT_EXCEPTION_MESSAGE);
        }
    }

}
