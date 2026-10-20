package pet.dragon;

import pet.Pet;

// 체력이 가장 높을 때 진화하는 드래곤
public class SteelDragon extends Dragon{

    public SteelDragon(Dragon base){
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

    // 강철 멘탈: 드래곤의 추가 행복도 감소(-5)를 상쇄
    @Override
    public void passTime(){
        super.passTime();
        setHappiness(getHappiness() + 5);
    }

    @Override
    public void makeSound(){
        System.out.println("쿠구구궁...! ⚙️");
    }

    @Override
    public void specialAction(){
        makeSound();
        System.out.println(getName() + "이(가) 강철 비늘을 번쩍이며 바위를 부숴요! 🛡️");
    }

    @Override
    public void showHappyImage() {
        System.out.println("                       /\\ /\\");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /##\\__        |  [@]      >");
        System.out.println("      /#/\\###\\__     |      __VVVV  ~⚡⚡");
        System.out.println("     /_/  \\_____\\__  |#####/");
        System.out.println("         [][][][][][]|     \\__^^^^>");
        System.out.println("        /####           _______/");
        System.out.println("  <#>__/   _________   /");
        System.out.println("          [_]\\    [_]\\/");
        System.out.println("          VVV     VVV");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("                       /\\ /\\");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /..\\__        |  [-]      >");
        System.out.println("      /./\\.,.\\__     |      __VVVV|  ~ 끼익... 녹슬었어요");
        System.out.println("     /_/  \\_____\\__  |.,.,./  ^^^^|");
        System.out.println("         [].[],[].[],|     \\______/");
        System.out.println("        /.,.,           /");
        System.out.println("  <.>__/   _________   /");
        System.out.println("          [_]\\    [_]\\/");
        System.out.println("          VVV     VVV");
    }
}
