package com.who307.section03.abstraction;

public class CarRacer {

    // 1. 클래스 직하단(멤버 변수 위치)으로 이동 (private 사용 가능)
    private final Car myCar = new Car();

    // 2. 클래스 직하단으로 메서드 정의 이동 (public 사용 가능)
    public void startUp() {
        myCar.startUp();
    }

    public void stepAccelerator() {
        myCar.go();
    }

    public void stepBrake() {
        myCar.stop();
    }

    public void turnOff() {
        myCar.turnOff();
    }


}