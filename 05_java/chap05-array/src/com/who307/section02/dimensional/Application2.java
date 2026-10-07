package com.who307.section02.dimensional;

import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        // 3명 학생의 국어, 영어, 수학 점수를 저장할 2차원 배열
        int[][] scores = {
                {80, 78, 67}, {90, 77, 95}, {70, 89, 94}
        };

        // 각 학생의 총점과 평균 계산 및 출력
        for(int i = 0; i < scores.length; i++){
            int sum = 0;
            for(int j = 0; j < scores[i].length; j++){
                sum += scores[i][j]; // 현재 학생의 j번째 과목점수 누적
            }
            double avg = sum / (double) scores[i].length;

            System.out.println((i + 1) + "번 학생의 총점 : " + sum);
            System.out.println((i + 1) + "번째 학생의 평균 : " + avg);
        }

        // 학생 수와 과목 수 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.print("학생 수를 입력: ");
        int studentCount = sc.nextInt();
        System.out.print("과목 수를 입력: ");
        int subjectCount = sc.nextInt();

        // 입력받은 수로 배열 생성
        int[][] studentArr = new int[studentCount][subjectCount];

        // 점수 입력받기
        for(int i = 0; i < studentArr.length; i++){
            for(int j = 0; j < studentArr[i].length; j++) {
                System.out.print((i + 1) + "번째 학생의 점수를 입력: ");
                studentArr[i][j] = sc.nextInt();
            }
        }
        // 순회해서 출력해보기
        for(int a = 0; a < studentArr.length; a++){
            System.out.println((a + 1) + "번째 학생 점수");
            for(int b = 0; b < studentArr[a].length; b++){
                System.out.print(studentArr[a][b] + " ");
            }
            System.out.println();
        }
    }
}
