/** array 배열
 * 관련된 값들을 하나의 목록으로 묶어서 관리한다
 */

const fruits = ["바나나", "복숭아", "키위"];
console.log(fruits);

//배열이름[인덱스]
// 배열에서는 위치를 나타내는 번호(index)가 있다. 인덱스는 1이 아니라 0부터 시작한다.
console.log(fruits[1]);

console.log(fruits[3]); // undefined 반환
console.log(fruits.length);

// 자바스크립트 배열(Array)의 모든 내장 기능 목록 출력하기
console.log(Object.getOwnPropertyNames(Array.prototype));

fruits[1] = "딸기";
console.log(fruits);

// console.log(fruits[0]);
// console.log(fruits[1]);
// console.log(fruits[2]);

// for문을 활용한 배열 순회
for (let i = 0; i < fruits.length; i++) {
    console.log(fruits[i]);
}
for (let item of fruits) {
    console.log(item);
}

console.log(typeof fruits);
console.log(Array.isArray(fruits)); // 배열인지 확인

// 1차원 배열에서의 얕은 복사 (원본에 영향 없음)
// 중첩된 객체/배열이 있을 때의 얕은 복사 (원본에 영향 생김)
const shallowCopy = [...fruits];

// 깊은 복사 (내부의 내부까지 완전히 독립)
const deepCopy = structuredClone(fruits);
