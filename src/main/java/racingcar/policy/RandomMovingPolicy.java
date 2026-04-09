package racingcar.policy;

import racingcar.number.NumberGenerator;

public class RandomMovingPolicy implements MovingPolicy {

    private static final int MOVABLE_STANDARD = 4;
    private final NumberGenerator numberGenerator;

    public RandomMovingPolicy(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public boolean isMovable() {
        return numberGenerator.generate() >= MOVABLE_STANDARD;
    }
}
