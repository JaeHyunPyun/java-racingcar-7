package racingcar;

public class Car {

    private String name;
    private int location;

    public Car(String name) {
        validateNameLength(name);
        this.name = name;
        this.location = 0;
    }

    public String getName(){
        return name;
    }

    private void validateNameLength(String name){
        if(name.length() > 5){
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

}
