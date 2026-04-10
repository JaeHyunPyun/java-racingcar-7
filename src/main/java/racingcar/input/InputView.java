package racingcar.input;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public UserInput getUserInput() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은(,)기준으로 구분)");
        String carNamesInput = scanner.nextLine();

        System.out.println("시도할 횟수는 몇회인가요?");
        int attemptCountInput = scanner.nextInt();

        return UserInput.from(CarNames.from(carNamesInput), AttemptCount.from(attemptCountInput));
    }

}
