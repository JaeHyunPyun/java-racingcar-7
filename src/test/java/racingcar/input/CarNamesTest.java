package racingcar.input;

import static racingcar.constant.ExceptionMessage.DUPLICATE_EXCEPTION_MESSAGE;
import static racingcar.constant.ExceptionMessage.INVALID_NAME_FORMAT_EXCEPTION_MESSAGE;
import static racingcar.constant.ExceptionMessage.OVER_NAME_LENGTH_EXCEPTION_MESSAGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarNamesTest {

    @Test
    @DisplayName("입력에 차 이름이 하나만 존재할때는 하나의 이름만 존재하는 리스트를 반환한다.")
    void from_SingleCarName_ReturnSingleElementList(){
        //given
        String input = "test1";

        //when
        CarNames carNames = CarNames.from(input);

        //then
        Assertions.assertThat(carNames.names()).containsExactly("test1");
    }

    @Test
    @DisplayName("입력에 차 이름이 여러개 존재할때는 해당 이름을 모두 담은 리스트를 반환한다.")
    void from_MultipleCarName_ReturnMultipleElementList(){
        //given
        String input = "test1,test2,test3";

        //when
        CarNames carNames = CarNames.from(input);

        //then
        Assertions.assertThat(carNames.names()).containsExactly("test1", "test2", "test3");
    }

    @Test
    @DisplayName("쉼표 구분자 사이에 공백이 있는 경우, 예외를 던진다.")
    void from_IncludeEmptyNameBetween_throwException(){
        //given
        String input = "test1,,test2";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_NAME_FORMAT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("첫번째 쉽표 구분자 앞이 공백이 있는 경우, 예외를 던진다.")
    void from_IncludeEmptyNameFront_throwException(){
        //given
        String input = ",test1,test2";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_NAME_FORMAT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("마지막 쉽표 구분자 뒤에 공백이 있는 경우, 제외한 이름을 반환한다")
    void from_IncludeEmptyNameRear_throwException(){
        //given
        String input = "test1,test2,";

        //when
        CarNames carNames = CarNames.from(input);

        //then
        Assertions.assertThat(carNames.names()).containsExactly("test1", "test2");
    }

    @Test
    @DisplayName("이름에 공백이 포함되어 있더라도, 공백을 제외한 이름을 리스트로 반환한다.")
    void from_NamesWithSpace_ReturnTrimmedName(){
        //given
        String input = "  test1  ,  test2  ,  test3  ";

        //when
        CarNames carNames = CarNames.from(input);

        //then
        Assertions.assertThat(carNames.names()).containsExactly("test1", "test2", "test3");
    }

    @Test
    @DisplayName("이름에 구분자만 존재하는 경우, 예외를 반환하다.")
    void from_OnlyDelimiter_throwException(){
        //given
        String input = ",";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_NAME_FORMAT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("사용자가 공백만 입력한 경우, 예외를 던진다.")
    void from_EmptyName_throwException(){
        //given
        String input = "";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_NAME_FORMAT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("중복된 이름이 포함된 경우, 예외를 던진다.")
    void from_DuplicateNames_throwException(){
        //given
        String input = "test1,test1";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(DUPLICATE_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("5글자 초과한 이름이 포함된 경우, 예외를 던진다.")
    void from_OverMaxNameLength_throwException(){
        //given
        String input = "test12,test12323";

        //when, then
        Assertions.assertThatThrownBy(() -> CarNames.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(OVER_NAME_LENGTH_EXCEPTION_MESSAGE);
    }


}
