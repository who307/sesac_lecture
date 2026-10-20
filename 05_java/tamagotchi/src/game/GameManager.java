package game;

import pet.Pet;

import java.util.Scanner;

public class GameManager {

    private Pet pet;
    private int day = 1;
    private Scanner sc = new Scanner(System.in);
    private int maxAction = 4;
    private int maxDays = 10;
    private boolean gameOver = false;

    public GameManager(Pet pet) {
        this.pet = pet;
    }

    public void startGame(){
        System.out.println("================================");
        System.out.println("     나만의 다마고치 키우기");
        System.out.println("================================");
        System.out.println(pet.getName() + "을(를) 잘 돌봐주세요!");


        while (!gameOver && day <= maxDays){
            System.out.println("==============="+day+"일차=================");
            System.out.println("새로운 하루가 시작되었습니다!");
            pet.getInfo();

            // 하루 행동 제한
            for(int i = 0; i < maxAction; i++){
                showMenu();
                int choice = getChoice();

                if(choice == 0){
                    System.out.println("게임을 종료합니다.");
                    return;
                }

                petAction(choice);
                pet.passTime();
                checkWarning();
                checkGameOver();

                if (gameOver) {
                    System.out.println("보살핌이 부족해 펫과의 여정이 끝났습니다.");
                    System.out.println("게임을 종료합니다.");
                    return;
                }

            }

            if(!gameOver && day == maxDays){
                showEnding();
                return;
            }
            day++;

            if(day == 3 || day == 7){
                pet = pet.evolve(day);
                pet.showHappyImage();
                pet.makeSound();
            }
            }
        }

    // 행동 메뉴
    private void showMenu(){
        System.out.println("========== 행동 선택 ==========");
        System.out.println("1. 밥 주기");
        System.out.println("2. 산책하기");
        System.out.println("3. 놀아주기");
        System.out.println("4. 씻기기");
        System.out.println("5. 재우기");
        System.out.println("6. 특수행동");
        System.out.println("0. 게임 종료");
        System.out.print("무엇을 할까요? 행동을 선택하세요: ");
    }

    // 사용자 입력
    private int getChoice(){
        while(true){
            String input = sc.next().trim();
            try {
                int choice = Integer.parseInt(input);
                if(choice >= 0 && choice <= 6){
                    return choice;
                }else{
                    System.out.print("0~6 사이의 메뉴 숫자를 입력해주세요: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("메뉴 숫자를 입력해주세요: ");
            }
        }
    }

    // 메뉴(행동) 실행
    private boolean petAction(int choice){
       switch (choice){
           case 1:
               System.out.println("[밥 주기를 선택했어요!]");
               return pet.eat();
           case 2:
               System.out.println("[산책하기를 선택했어요!]");
               return pet.walk();
           case 3:
               System.out.println("[놀아주기를 선택했어요!]");
               return pet.play();
           case 4:
               System.out.println("[씻기기를 선택했어요!]");
               pet.clean();
               return true;
           case 5:
               System.out.println("[재우기를 선택했어요!]");
               pet.sleep();
               return true;
           case 6:
               pet.specialAction();
               return true;
       }
        return false;
    }

    // 게임 오버 체크
    private void checkGameOver(){
        if(pet.getHp() == 0){
            System.out.println(pet.getName() + "이(가) 지쳐 쓰러졌습니다.");
            gameOver = true;
        } else if(pet.getHunger()==100){
            System.out.println(pet.getName() + "이(가) 굶주림으로 생존에 실패했습니다.");
            gameOver = true;
        } else if(pet.getHappiness()==0){
            System.out.println(pet.getName() + "이(가) 집을 나갔습니다.");
            gameOver = true;
        }
    }

    // 펫 상태 경고
    private void checkWarning(){
        if (pet.getHunger() >= 80) {
            System.out.println("경고: 배가 많이 고파요!");
        }

        if (pet.getHappiness() <= 20) {
            System.out.println("경고: 행복도가 낮아요!");
        }

        if (pet.getHp() <= 20) {
            System.out.println("경고: 체력이 부족해요!");
        }

        if (pet.getCleanliness() <= 20) {
            System.out.println("경고: 몸이 많이 더러워요!");
        }
    }

    // 게임 엔딩
    private void showEnding(){
        System.out.println("===== 축하합니다! =====");
        System.out.println(pet.getName() + "와(과) "+ maxDays + "일을 함께 보냈어요!");
        pet.showHappyImage();
        pet.getInfo();

        // 상태 점수 계산
        int statusScore = (pet.getHappiness() + pet.getHp() + pet.getCleanliness() + (100 - pet.getHunger())) / 4;

        // 최종 점수
        int result = pet.getAffection() + pet.getEvolutionBonus() + statusScore;

        System.out.println("최종 점수: " + result);
        if (result >= 180) {
            System.out.println("최종 등급: S");
            System.out.println("야생의 우두머리가 되었어요!");
        } else if (result >= 140) {
            System.out.println("최종 등급: A");
            System.out.println("야생에 잘 적응했어요!");
        } else if (result >= 100) {
            System.out.println("최종 등급: B");
            System.out.println("시간이 걸렸지만 야생에 적응했어요!");
        } else {
            System.out.println("최종 등급: C");
            System.out.println("야생 적응에 실패해 보호소로 돌아왔어요.");
        }
    }

}

