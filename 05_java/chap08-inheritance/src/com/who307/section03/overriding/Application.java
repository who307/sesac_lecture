package com.who307.section03.overriding;

public class Application {
    public static void main(String[] args) {

        CoffeeVendingMachine coffeeVendingMachine = new CoffeeVendingMachine();

        System.out.println(coffeeVendingMachine.vend());
        coffeeVendingMachine.clean();
        coffeeVendingMachine.powerOn();
        
    }
}
