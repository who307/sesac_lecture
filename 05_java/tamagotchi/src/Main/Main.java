package Main;

import game.GameManager;
import pet.dragon.Dragon;
import pet.dog.Dog;
import pet.egg.Egg;
import pet.Pet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 다형성: 부모 타입(Pet)으로 자식 객체를 참조
        Pet pet;
        Scanner sc = new Scanner(System.in);
        System.out.print("펫의 이름은 무엇인가요?: ");
        String name = sc.nextLine().trim();
        System.out.println("펫의 종류를 선택하세요: 1. 강아지 2. 드래곤 3. 알");
        while (true){
            String input = sc.next().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 1) {
                    pet = new Dog(name);
                    break;
                } else if (choice == 2) {
                    pet = new Dragon(name);
                    break;
                } else if (choice == 3) {
                    pet = new Egg(name);
                    break;
                } else{
                    System.out.println("없는 번호를 선택하셨습니다. 다시 선택해 주세요");
                }
            } catch (NumberFormatException e) {
                System.out.print("메뉴 숫자를 입력해주세요: ");
            }
        }

        System.out.println("안녕 " + name + "!");
//        if (pet instanceof Dog) {
//            ((Dog) pet).specialDog();
//        }

        GameManager gameManager = new GameManager(pet);

        // 게임 시작
        gameManager.startGame();
    }
}