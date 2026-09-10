/** */

const students = [
    { name: "홍길동", score: 90 },
    { name: "권판다", score: 80 },
    { name: "유관순", score: 70 },
];

console.log(students[0].name);
console.log(students[1].name);
console.log(students[2].name);

// for문으로 이름 출력
for (let index = 0; index < students.length; index++) {
    const element = students[index].name;
    console.log("for문: ", element);
}

// forEach 사용 - 배열 요소 하나씩 전달받기
students.forEach((el) => {
    const element = el.name;
    console.log("forEach 메소드: ", element);
});
// forEach()는 콜백을 호출할 때 현재 요소와 인덱스를 전달해준다.
students.forEach((student, i) => {
    console.log(i + 1 + "번째 이름:" + student.name);
});
// forEach는 콜백함수의 반환값을 모아주지 않는다. forEach의 반환값은 undefined
const ignoredNames = students.forEach((student) => student.name);
console.log(ignoredNames);

// map: 각 요소를 콜백의 반환값으로 바꾼 결과를 새 배열로 만들어 준다.
const studendNames = students.map((student) => student.name);
console.log(studendNames);

// 원본 배열에 영향을 주지는 않는다.
console.log(students[0]);

// 점수에 5점을 더하여 배열로 반환받기
const adjustedScores = students.map((student) => {
    return {
        name : student.name,
        score : student.score + 5
    }
    ;
});
console.log(adjustedScores);
