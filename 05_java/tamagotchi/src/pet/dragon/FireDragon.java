package pet.dragon;

import pet.Pet;

// 행복도가 가장 높을 때 진화하는 드래곤
public class FireDragon extends Dragon{

    public FireDragon(Dragon base){
        super(base);
    }

    // 이미 진화한 상태이므로 더 이상 진화하지 않음
    @Override
    public Pet evolve(int day){
        return this;
    }

    @Override
    public int getEvolutionBonus(){
        return 40;
    }

    @Override
    public void makeSound(){
        System.out.println("크와아아앙!! 🔥");
    }

    // 화염 브레스: 특수행동 시 행복도 추가 상승
    @Override
    public void specialAction(){
        makeSound();
        System.out.println(getName() + "이(가) 거대한 화염 브레스를 뿜어요! 🔥🔥🔥");
        setHappiness(getHappiness() + 15);
        System.out.println("화염 브레스 보너스! 행복도 + 15");
    }

    @Override
    public void showHappyImage() {
        System.out.println("          (  )  (   )");
        System.out.println("           )(    ) (");
        System.out.println("                       /\\ /\\");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /  \\__        |  (@)      >");
        System.out.println("      / /\\   \\__     |      __VVVV  ~🔥🔥🔥🔥");
        System.out.println("     /_/  \\_____\\__  |     /");
        System.out.println("         )()()()()()(|     \\__^^^^>");
        System.out.println("        /               _______/");
        System.out.println(" 🔥<___/   _________   /");
        System.out.println("          /_/\\    /_/\\/");
        System.out.println("          VVV     VVV");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("                       /\\ /\\   . o O");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /  \\__        |  (-)      >");
        System.out.println("      / /\\   \\__     |      __VVVV|  ~ 연기만 폴폴...");
        System.out.println("     /_/  \\_____\\__  |     /  ^^^^|");
        System.out.println("         /\\/\\/\\/\\/\\/\\|     \\______/");
        System.out.println("        /               /");
        System.out.println("  ~~___/   _________   /");
        System.out.println("          /_/\\    /_/\\/");
        System.out.println("          VVV     VVV");
    }
}
