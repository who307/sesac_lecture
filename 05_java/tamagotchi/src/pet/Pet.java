package pet;

public abstract class Pet {

    /* 초기 세팅
     * getter & setter 필요에 따라 추가 */

    // 공통 속성
    private String name;
    private int hp; // 체력
    private int happiness;
    private int hunger;
    private int cleanliness;
    private int affection;  // 친밀도

    public Pet(String name) {
        this.name = name;
        this.hp = 100;
        this.happiness = 80;
        this.hunger = 0;
        this.cleanliness = 100;
        this.affection = 0;
    }

    // 진화시 이전 펫의 상태를 이어서 받아오기 위한 복사 생성자
    protected Pet(Pet prev){
        this.name = prev.name;
        this.hp = prev.hp;
        this.happiness = prev.happiness;
        this.hunger = prev.hunger;
        this.cleanliness = prev.cleanliness;
        this.affection = prev.affection;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getHunger() {
        return hunger;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getAffection() {
        return affection;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = limitNumber(happiness);
    }

    public void setHp(int hp) {
        this.hp = limitNumber(hp);
    }


    // 공통 기능
    public boolean eat() {

        if (hunger <= 10) {
            showUnhappyImage();
            affection -= 5;
            checkStatus();
            System.out.println(name + "은(는) 배가 불러서 밥을 먹지 않아요!");
            return false;
        }
        // 과식 처리
        if (hunger <= 30) {
            hp -= 10;
            hunger -= 10;
            affection -= 5;
            happiness -= 10;
            showUnhappyImage();
            System.out.println("너무 배불러서 과식했어요!");
            checkStatus();
            return true;
        }
        showHappyImage();
        System.out.println(name + "이(가) 밥을 맛있게 먹어요!");
        hunger -= 25;
        hp += 5;
        happiness += 10;
        checkStatus();
        return true;
    }

    public boolean walk() {
        if (hp <= 20) {
            showUnhappyImage();
            System.out.println(name + "은(는) 너무 지쳐서 산책할 수 없어요!");
            return false;
        }
        showHappyImage();
        System.out.println(name + "이(가) 산책을 갑니다!");
        hunger += 15;
        happiness += 15;
        hp -= 15;
        affection += 5;
        cleanliness -= 10;
        checkStatus();
        return true;
    }

    public boolean play() {
        if (hp <= 20) {
            showUnhappyImage();
            System.out.println(name + "은(는) 너무 지쳐서 놀 수 없어요!");
            return false;
        }
        showHappyImage();
        System.out.println(name + "이(가) 신나게 놀아요!");
        affection += 10;
        hunger += 10;
        happiness += 20;
        hp -= 5;
        cleanliness -= 5;
        checkStatus();
        return true;
    }

    public void clean() {
        showHappyImage();
        System.out.println(name + "이(가) 씻어요!");
        cleanliness += 20;
        happiness += 5;
        checkStatus();
    }

    public void sleep() {
        System.out.println(name + "이(가) 잠에 듭니다!");
        hp += 20;
        hunger += 15;
        happiness += 15;
        checkStatus();
        System.out.println("Zzzzz...");
    }

    // 시간 관련
    public void passTime() {
        hunger += 5;
        happiness -= 3;
        cleanliness -= 5;

        System.out.println("시간이 흐르고 있습니다...");
        checkStatus();
    }

    // 진화 점수 (게임 엔딩시 점수 계산)
    // 진화된 펫 클래스에서 오버라이딩해서 점수 리턴
    public int getEvolutionBonus(){
        return 0;
    }

    public void getInfo() {
        System.out.println("========== 펫 상태 ==========");
        System.out.println("이름: " + name);
        System.out.println("체력: " + hp);
        System.out.println("행복도: " + happiness);
        System.out.println("배고픔: " + hunger);
        System.out.println("청결도: " + cleanliness);
        System.out.println("친밀도: " + affection);
        System.out.println("============================");
    }

    // 펫 고유 기능
    public abstract void makeSound();
    public abstract void specialAction();
    public abstract void showHappyImage();
    public abstract void showUnhappyImage();


    // pet 상태값 리밋 체크
    // 지정 최소/최대값 범위를 벗어나지 않도록 하기 위함
    private void checkStatus() {
        hunger = limitNumber(hunger);
        hp = limitNumber(hp);
        happiness = limitNumber(happiness);
        cleanliness = limitNumber(cleanliness);
        affection = limitNumber(affection);
    }

    private int limitNumber(int value){
        return Math.max(0, Math.min(100, value));
    }

    // 펫 진화 (매일 아침 호출됨)
    // 진화하는 펫 클래스에서 오버라이딩해서 진화 조건을 만족하면 진화된 객체 리턴
    // 기본은 진화하지 않으므로 자기 자신을 리턴
    public Pet evolve(int day){
        return this;
    }
}
