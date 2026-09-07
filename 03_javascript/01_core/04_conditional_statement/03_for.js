/**
 * 반복문 for
 * 명확한 반복 횟수를 지정하여 코드를 실행한다
 *
 * for (초기화; 조건식; 증감식){
 * // 반복 실행할 코드
 * }
 *
 * 1. 초기화: 반복 변수(i)를 초기값으로 설정
 * 2. 조건식: 반복을 계속할지 여부 결정. true일 경우 반복이 실행
 * 3. 증감식: 반복 변수(i)를 조정하여 조건식에 영향을 줌
 */

for (let i = 1; i <= 5; i++) {
    console.log(`반복 횟수 : ${i}`);
} // 초기화 -> 조건식 확인 -> 실행문 실행 -> 증감식 실행 순서로 반복된다

/** 배열 요소 출력 */

let fruits = ["apple", "banana", "cherry"];
console.log(fruits.length);
for (let i = 0; i < fruits.length; i++) {
        console.log(`${fruits[i]}`);

}

for (let i in fruits) {
    console.log(fruits[i]);
}

/** 1부터 10까지 숫자 중 짝수만 출력 */
 
for(let i = 1; i <=10; i++){
    if(i % 2 === 0){ // 2로 나누었을때 나머지가 0이면 짝수
        console.log(i);
    }
}

let stars = "";
for (let i = 1; i <= 5; i++) {
    stars += "★";
    console.log(stars);
}