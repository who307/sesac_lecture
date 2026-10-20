package pet.dog;

import pet.Pet;

public class Dog extends Pet {

    public Dog(String name){
        super(name);
    }

    public Dog(Pet prev) {
        super(prev);
    }

    @Override
    public boolean walk(){
        boolean isSuccess = super.walk();
        if(!isSuccess){
            return false;
        }
        setHappiness(getHappiness() + 10);
        System.out.println("산책 보너스! 행복도 + 10");

        return true;
    }

    @Override
    public int getEvolutionBonus(){
        return 10;
    }

    @Override
    public void passTime(){
        super.passTime();
        setHappiness(getHappiness() - 2);
    }

    @Override
    public void makeSound(){
        System.out.println("멍멍 왈왈!");
    }

    @Override
    public void specialAction(){
        System.out.println(getName() + "이(가) 꼬리를 흔들며 뛰어요!");
    }

//    public void specialDog(){
//        System.out.println("*****test*****");
//    }

    public void showHappyImage() {
        System.out.println(" / \\__");
        System.out.println("( ^  ^ )");
        System.out.println(" /     \\");
        System.out.println("/_______\\");
    }

    public void showUnhappyImage() {
        System.out.println(" / \\__");
        System.out.println("( -  - )");
        System.out.println(" /     \\");
        System.out.println("/_______\\");
    }

    // 펫 진화
    @Override
    public Pet evolve(int day){
        if(day == 3){
            System.out.println("===== 축하합니다! =====");
            System.out.println(getName()+ "(이)가 날개를 얻었어요!");
            return new FlyDog(this);
        }
        return this;
    }

}
