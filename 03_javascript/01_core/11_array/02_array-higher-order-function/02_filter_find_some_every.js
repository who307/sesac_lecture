/** filter, find, some, every*/

const students = [
    { name: "홍길동", score: 90 },
    { name: "권판다", score: 80 },
    { name: "유관순", score: 85 },
];
// filter : 콜백함수의 반환결과가 true인 요소만 모아 새로운 배열로 만듦
const highScorers = students.filter((students) => students.score >= 85);
console.log(highScorers);

// find() - 처음으로 조건을 통과한 요소 하나를 반환
const firstHighScorer = students.find((students) => students.score >= 85);
console.log(firstHighScorer?.name);

console.log(students.find((student) => student.score >= 100)); // undefined
// find로 맞는 요소를 찾지 못하면 undefined를 반환하기 때문에 안정적인 객체접근을 위해
// 옵셔널 체이닝 연산자(?.)를 사용할 수 있다.

console.log(students.filter((student) => student.score >= 100)); // []

// some - 조건에 맞는 요소가 한개라도 있는지 확인, 불리언 값 반환
const hasHighScorer = students.some((student) => student.score >= 85);
console.log(hasHighScorer);

const allHighScorer = students.every((student) => student.score >= 80);
console.log(allHighScorer);
