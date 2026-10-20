package pet.egg;

import pet.Pet;
import pet.dog.Dog;
import pet.dragon.Dragon;

// 알: 어떤 행동에도 수치가 변하지 않고, 행동 횟수만 기록
// 부화하는 날 가장 많이 한 행동에 따라 강아지 또는 드래곤이 태어남
// 밥은 먹을 수 없어 거부함
public class Egg extends Pet {

    // 부화하는 날 (이 날 아침에 부화)
    // 강아지의 3일차 진화와 겹치지 않도록 2일차에 부화
    private static final int HATCH_DAY = 2;

    // 행동 횟수 (밥은 먹을 수 없으므로 기록하지 않음)
    private int walkCount;
    private int playCount;
    private int cleanCount;
    private int sleepCount;

    // 부화 실패 여부 (밥만 줘서 기록된 행동이 없으면 부화하지 못함)
    private boolean hatchFailed;

    public Egg(String name){
        super(name);
    }

    // 알은 밥을 먹을 수 없음 (행동 횟수는 소모됨)
    @Override
    public boolean eat(){
        System.out.println("알은 아직 밥을 먹을 수 없어요!");
        return false;
    }

    @Override
    public boolean walk(){
        walkCount++;
        wobble();
        return true;
    }

    @Override
    public boolean play(){
        playCount++;
        wobble();
        return true;
    }

    @Override
    public void clean(){
        cleanCount++;
        wobble();
    }

    @Override
    public void sleep(){
        sleepCount++;
        wobble();
    }

    // 알은 시간이 흘러도 수치가 변하지 않음
    @Override
    public void passTime(){
        System.out.println("시간이 흐르고 있습니다...");
    }

    // 산책을 가장 많이 했으면 강아지, 그 외(놀이 위주)는 드래곤
    @Override
    public Pet evolve(int day){
        if(day != HATCH_DAY){
            return this;
        }
        if(walkCount + playCount + cleanCount + sleepCount == 0){
            hatchFailed = true;
            showUnhappyImage();
            System.out.println("===== 엔딩: 깨어나지 못한 알 =====");
            System.out.println(getName() + "은(는) 끝내 알에서 깨어나지 못했어요...");
            System.out.println("알에게는 밥보다 따뜻한 손길이 필요했나 봐요.");
            return this;
        }

        System.out.println("알에 금이 가기 시작해요... 쩌적!");

        boolean walkMost = walkCount > playCount
                && walkCount > cleanCount && walkCount > sleepCount;
        if(walkMost){
            System.out.println(getName() + "이(가) 강아지로 태어났어요!");
            return new Dog(this);
        }
        System.out.println(getName() + "이(가) 드래곤으로 태어났어요!");
        return new Dragon(this);
    }

    public boolean isHatchFailed(){
        return hatchFailed;
    }

    private void wobble(){
        System.out.println(getName() + "이(가) 살짝 흔들려요...");
    }

    @Override
    public void makeSound(){
        System.out.println("...톡톡");
    }

    @Override
    public void specialAction(){
        System.out.println(getName() + "을(를) 따뜻하게 품어줬어요.");
        wobble();
    }

    @Override
    public void showHappyImage() {
        System.out.println("   ___");
        System.out.println("  /   \\");
        System.out.println(" | ^ ^ |");
        System.out.println("  \\___/");
    }

    @Override
    public void showUnhappyImage() {
        System.out.println("   ___");
        System.out.println("  /   \\");
        System.out.println(" | - - |");
        System.out.println("  \\___/");
    }

}
