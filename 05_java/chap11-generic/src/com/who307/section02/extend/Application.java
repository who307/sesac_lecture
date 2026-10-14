package com.who307.section02.extend;

public class Application {
    public static void main(String[] args) {

        RabbitFarm<Rabbit> farm1 = new RabbitFarm<>(new Rabbit());
        RabbitFarm<Bunny> farm2 = new RabbitFarm<>(new Bunny());
        RabbitFarm<DrunkenBunny> farm3 = new RabbitFarm<>(new DrunkenBunny());

//        RabbitFarm<snake> farm4 = new RabbitFarm<snake>(new snake());

        Rabbit rabbit = farm1.getAnimal();
        Bunny bunny = farm2.getAnimal();
        DrunkenBunny drunkenBunny = farm3.getAnimal();

        rabbit.cry();
        bunny.cry();
        drunkenBunny.cry();
    }
}
