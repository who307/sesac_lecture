// for...of와 이터러블
// 이터러블은 값을 순서대로 하나씩 꺼낼 수 있는 값이다. 배열과 문자열이 대표적이다.

const fruits = ["🍎", "🍇", "🍌"];

for (let i = 0; i < fruits.length; i++) {
    console.log("일반 for문:", i, fruits[i]);
}

// for...of 문
for (item of fruits) {
    console.log("for...of", item);
}

const message = "안녕";
for (const character of message) {
    console.log("글자", character);
}

const student = {
    name: "홍길동",
    score: 80,
};
