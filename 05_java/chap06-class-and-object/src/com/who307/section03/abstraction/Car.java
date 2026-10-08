package com.who307.section03.abstraction;

public class Car {
    private boolean isOn;
    private int speed;

    public void startUp() {
        if (isOn) {
            System.out.println("이미 시동이 걸려있습니다.");
        } else {
            this.isOn = true;
            System.out.println("시동을 걸었습니다. 출발 준비가 완료되었습니다.");
        }
    }

    public void go() {
        if (isOn) {
            System.out.println("차량이 앞으로 나아갑니다.");
            this.speed += 10;
            System.out.println("현재 속도는 " + this.speed + "km/h 입니다.");
        } else {
            System.out.println("시동이 꺼져있습니다. 먼저 시동을 걸어주세요.");
        }
    }

    public void stop() {
        if (isOn) {
            if (this.speed > 0) {
                this.speed = 0;
                System.out.println("브레이크를 밟았습니다. 차량이 멈춥니다.");
            } else {
                System.out.println("차량이 이미 멈춰있는 상태입니다.");
            }
        } else {
            System.out.println("시동이 꺼져있는 상태입니다.");
        }
    }

    public void turnOff() {
        if (isOn) {
            if (this.speed > 0) {
                System.out.println("달리는 상태에서는 시동을 끌 수 없습니다. 먼저 차를 세우세요.");
            } else {
                this.isOn = false;
                System.out.println("시동을 껐습니다.");
            }
        } else {
            System.out.println("이미 시동이 꺼져있습니다.");
        }
    }
}