package racingcar.input;

import static racingcar.constant.ExceptionMessage.DUPLICATE_EXCEPTION_MESSAGE;
import static racingcar.constant.ExceptionMessage.INVALID_NAME_FORMAT_EXCEPTION_MESSAGE;
import static racingcar.constant.ExceptionMessage.OVER_NAME_LENGTH_EXCEPTION_MESSAGE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public record CarNames(
    List<String> names
) {

    private static final String DELIMITER = ",";

    private static final int MAX_NAME_LENGTH = 5;

    public static CarNames from(String userInput) {
        List<String> parsedNames = Arrays.stream(userInput.split(DELIMITER))
            .map(String::trim)
            .peek(CarNames::validateNameFormat)
            .toList();

        validateBlankInput(parsedNames);
        validateDuplicateNames(parsedNames);

        return new CarNames(parsedNames);
    }

    private static void validateNameFormat(String name){
        if (name.isEmpty()) {
            throw new IllegalArgumentException("잘못된 이름이 포함되어 있습니다.");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야합니다.");
        }
    }

    private static void validateBlankInput(List<String> parsedNames) {
        if (parsedNames.isEmpty()) {
            throw new IllegalArgumentException("잘못된 이름이 포함되어 있습니다.");
        }
    }

    private static void validateDuplicateNames(List<String> parsedNames) {
        if (new HashSet<>(parsedNames).size() != parsedNames.size()) {
            throw new IllegalArgumentException("중복된 이름이 포함되어 있습니다.");
        }
    }

}
