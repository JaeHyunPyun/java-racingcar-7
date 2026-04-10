package racingcar.car;

import racingcar.policy.MovingPolicy;

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

    public void move(MovingPolicy movingPolicy){
        if(movingPolicy.isMovable()){
            location++;
        }
    }

    public int getLocation(){
        return location;
    }

    public int compareLocation(Car other){
        return Integer.compare(this.location, other.getLocation());
    }

    private void validateNameLength(String name){
        if(name.length() > 5){
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

}
