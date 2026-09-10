/**배열 메소드 */

const foods = ["짜장면", "짬뽕", "볶음밥", "탕수육", "향라대하"];

// push: 배열 끝에 요소를 추가하고 새 길이를 반환
const count = foods.push("오향장육");
console.log(foods);
console.log(count);

// pop: 배열 끝의 요소를 제거
const removed = foods.pop();
console.log(removed);

// unshift : 배열의 맨 앞에 요소 추가, 변경 후 길이 반환
foods.unshift("유산슬");
console.log(foods);
// shift(): 배열의 맨 앞 요소 제거, 제거한 요소 반환
foods.shift();
console.log(foods);

// indexOf('값'): 값이 처음으로 나오는 인덱스 반환
const animals = ["고양이", "강아지", "토끼", "햄스터", "고양이"];
console.log(animals.indexOf("고양이"));
console.log(animals.indexOf("호랑이")); // 없으면 -1 반환

// include("값"): 값이 있으면 true, 없으면 false
console.log(animals.includes("고양이"));
console.log(animals.includes("사슴"));

console.log(animals);
// 중복된 배열 값 뺀 배열 반환
const uniqueArr = [...new Set(animals)];
console.log(uniqueArr);