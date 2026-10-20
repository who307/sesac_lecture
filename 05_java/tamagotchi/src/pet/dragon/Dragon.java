package pet.dragon;

import pet.Pet;

public class Dragon extends Pet{

    public Dragon(String name){
        super(name);
    }

    // 진화용 생성자 (알에서 부화할 때도 사용)
    public Dragon(Pet prev){
        super(prev);
    }

    // 5일차에 진화, 진화 시점의 가장 높은 상태값에 따라 진화 형태 결정
    // 행복도 → 불꽃 드래곤, 청결도 → 얼음 드래곤, 체력 → 강철 드래곤
    @Override
    public Pet evolve(int day){
        if(day != 5){
            return this;
        }
        System.out.println("어라...? " + getName() + "의 몸이 빛나기 시작해요! ✨");

        int happiness = getHappiness();
        int cleanliness = getCleanliness();
        int hp = getHp();

        Dragon evolved;
        String evolvedName;
        if(happiness >= cleanliness && happiness >= hp){
            evolved = new FireDragon(this);
            evolvedName = "불꽃 드래곤";
        } else if(cleanliness >= hp){
            evolved = new IceDragon(this);
            evolvedName = "얼음 드래곤";
        } else {
            evolved = new SteelDragon(this);
            evolvedName = "강철 드래곤";
        }

        System.out.println(getName() + "이(가) " + evolvedName + "(으)로 진화했어요!");
        return evolved;
    }

    @Override
    public boolean play(){
        boolean isSuccess = super.play();
        if(!isSuccess){
            return false;
        }
        setHappiness(getHappiness() + 10);
        System.out.println("하늘을 날며 놀았어요! 비행 보너스! 행복도 + 10");

        return true;
    }

    @Override
    public int getEvolutionBonus(){
        return 20;
    }

    @Override
    public void passTime(){
        super.passTime();
        setHappiness(getHappiness() - 5);
    }

    @Override
    public void makeSound(){
        System.out.println("크아아앙!");
    }

    @Override
    public void specialAction(){
        makeSound();
        System.out.println(getName() + "이(가) 하늘을 향해 불을 뿜어요! 🔥");
    }

    public void showHappyImage() {
        System.out.println("            /\\ /\\");
        System.out.println("     /\\    / \\\\ \\___");
        System.out.println("    /  \\_ |  (@)   >");
        System.out.println("   /_/\\__\\|    _VV");
        System.out.println("      /\\/\\|    \\_^^>");
        System.out.println("  <>__/    ____/");
        System.out.println("       /_/\\ /_/\\");
        System.out.println("       VV   VV");
    }

    public void showUnhappyImage() {
        System.out.println("            /\\ /\\  ~");
        System.out.println("     /\\    / \\\\ \\___~");
        System.out.println("    /  \\_ |  (-)   >");
        System.out.println("   /_/\\__\\|    _VV|");
        System.out.println("      /\\/\\|    \\__/");
        System.out.println("  <>__/    ____/");
        System.out.println("       /_/\\ /_/\\");
        System.out.println("       VV   VV");
    }

}
