package pet.dog;

import pet.Pet;

public class SkyDog extends FlyDog {

    public SkyDog(Pet prev) {
        super(prev);
    }

    @Override
    public int getEvolutionBonus(){
        return 35;
    }

    @Override
    public void specialAction(){
        System.out.println(getName() + "이(가) 구름에서 휴식해요!");
        setHappiness(getHappiness()+10);
        setHp(getHp() + 10);
        System.out.println("행복도/체력 +10 보너스를 얻었어요!");
    }

    @Override
    public void showHappyImage() {
        System.out.println("       .  *  .");
        System.out.println("    __/\\__/\\__");
        System.out.println("  <(  ^  ^  )>  *");
        System.out.println("     /  /\\  \\");
        System.out.println("  __/__/  \\__\\__");
        System.out.println("       ~~~~~");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("       .  *  .");
        System.out.println("    __/\\__/\\__");
        System.out.println("  <(  -  -  )>  *");
        System.out.println("     /  /\\  \\");
        System.out.println("  __/__/  \\__\\__");
        System.out.println("       ~~~~~");
    }

}
