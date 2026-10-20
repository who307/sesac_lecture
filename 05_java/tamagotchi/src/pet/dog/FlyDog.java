package pet.dog;

import pet.Pet;

public class FlyDog extends Dog {

    public FlyDog(Pet prev) {
        super(prev);
    }

    @Override
    public int getEvolutionBonus(){
        return 20;
    }

    @Override
    public void specialAction(){
        System.out.println(getName() + "이(가) 하늘을 날아요! 두둥실");
        setHappiness(getHappiness()+10);
        System.out.println("행복도 +10 보너스를 얻었어요!");
    }

    @Override
    public void showHappyImage() {
        System.out.println("  \\  /\\__/\\  /");
        System.out.println("   \\/ ( ^  ^ ) \\/");
        System.out.println("      /  ||  \\");
        System.out.println("     /___||___\\");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("  \\  /\\__/\\  /");
        System.out.println("   \\/ ( -  - ) \\/");
        System.out.println("      /  ||  \\");
        System.out.println("     /___||___\\");
    }

    // 펫 진화
    @Override
    public Pet evolve(int day){
        if(day == 7){
            System.out.println("===== 축하합니다! =====");
            System.out.println(getName()+ "(이)가 하늘의 마법사로 진화했어요!");
            return new SkyDog(this);
        }
        return this;
    }

}
