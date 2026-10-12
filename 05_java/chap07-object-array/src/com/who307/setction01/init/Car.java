package com.who307.setction01.init;

public class Car {

    private String modelName;
    private int maxSpeed;

    public Car(String modelName, int maxSpeed){
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
    }

    public void driveMaxSpeed(){
        System.out.println(this.modelName + "가 " + this.maxSpeed + "로 쌩~ 달려갑니다!");
    }
}
