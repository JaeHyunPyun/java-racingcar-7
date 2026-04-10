package racingcar.number;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {

    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 9;

    @Override
    public int generate(){
        return Randoms.pickNumberInRange(LOWER_BOUND, UPPER_BOUND);
    }

}
