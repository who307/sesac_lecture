package pet.dragon;

import pet.Pet;

// 청결도가 가장 높을 때 진화하는 드래곤
public class IceDragon extends Dragon{

    public IceDragon(Dragon base){
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

    // 얼음 목욕: 씻을 때 행복도 추가 상승
    @Override
    public void clean(){
        super.clean();
        setHappiness(getHappiness() + 10);
        System.out.println("차가운 얼음 목욕! 행복도 + 10");
    }

    @Override
    public void makeSound(){
        System.out.println("키이이잉~ ❄️");
    }

    @Override
    public void specialAction(){
        makeSound();
        System.out.println(getName() + "이(가) 주변을 꽁꽁 얼려 눈꽃을 피워요! ❄️❄️❄️");
    }

    @Override
    public void showHappyImage() {
        System.out.println("      *        ❄️         *");
        System.out.println("                       /\\ /\\");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /**\\__        |  (o)      >");
        System.out.println("      /*/\\***\\__     |      __VVVV  ~❄️❄️❄️");
        System.out.println("     /_/  \\_____\\__  |     /");
        System.out.println("         *^*^*^*^*^*^|     \\__^^^^>");
        System.out.println("        /               _______/");
        System.out.println(" ❄️<___/   _________   /");
        System.out.println("          /_/\\    /_/\\/");
        System.out.println("          VVV     VVV");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("                       /\\ /\\");
        System.out.println("        /\\            / \\\\ \\_____");
        System.out.println("       /  \\__        |  (T)      >");
        System.out.println("      / /\\   \\__     |      __VVVV|  ~ 녹아내려요...");
        System.out.println("     /_/  \\_____\\__  |     /  ^^^^|");
        System.out.println("         /\\/\\/\\/\\/\\/\\|     \\______/");
        System.out.println("        /       '    '  /");
        System.out.println("  <>___/   _________   /");
        System.out.println("          /_/\\  ' /_/\\/");
        System.out.println("          VVV     VVV");
        System.out.println("      ~~~~~~~~~~~~~~~~~~~~~~");
    }
}
