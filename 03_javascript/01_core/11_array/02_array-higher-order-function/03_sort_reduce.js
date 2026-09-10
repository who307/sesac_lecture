/** sort, reduce */

const numbers = [3, 1, 4, 12, 8];
const sorted = numbers.sort(); // 원본 배열을 정렬한다
console.log(sorted);

console.log(sorted === numbers);
numbers.sort((a, b) => b - a);
/**
 * 반환값 음수 : a를 b보다 앞에 놓는다.
 * 반환값 양사 : a를 b보다 뒤에 놓는다.
 * 0 : 이 비교 기준에서 같은 순위로 취급한다.
 */
console.log(numbers);

const amounts = [1000, 2000, 5000, 200, 400];
let sum = 0;
for (let i = 0; i < amounts.length; i++) {
    sum += amounts[i];
}
console.log(sum);

// 누적하기 - reduce 사용(반환값을 받아 다음 호출로 넘겨준다) 원본 배열 건들지 않음
const total = amounts.reduce((sum, current) => sum + current, 0);
console.log(total);

console.log("빈 배열 합계:",[].reduce((sum, current) => sum + current, 0));
