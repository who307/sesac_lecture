/**
 * 함수의 기본 구조
 * 특정 동작을 이름 붙여 재사용할 수 있기ㅔ 만든 코드 묶음이다
 */

const area1 = 10 * 20;
const area2 = 30 * 40;
const area3 = 50 * 60;
console.log(area1, area2, area3);

// 함수 선언문
function calculateArea(width, height) {
    // 1. 함수 이름과 매개변수(parameter)
    console.log("함수 안으로 들어왔습니다."); // 2. 실행할 로직
    const area = width * height;
    return area; // 3. 반환값 - 호출한 곳으로 값 전달
}

// 함수 호출 및 결과 활용
const result = calculateArea(10, 20); // 인자(argument)전달하며 함수 호출
console.log(result);

console.log(calculateArea(30, 40));
console.log(calculateArea(50, 60));

/**
 * 매개변수(parameter) 인수(argument)
 * 함수를 정의할 때 설정하는 통로를 매개변수, 실제 호출할 때 넘기는 값을 인수라고 한다.
 */

// 매개변수는 함수가 호출될 때 값을 받기 위해 함수 내부에 만들어지는 지역 변수라고 볼 수 있다.
function greet(name) {
    console.log(name); // 내부에서는 자유롭게 접근 가능
    console.log(arguments);
    return `${name}님 반갑습니다!`;
}
console.log(greet("홍길동"));

// console.log(name); // ReferenceError: 함수 내부(지역 스코프)에서만 살아있다.

// 인수 개수 불일치
console.log(greet()); // 인수가 부족하면 undefined가 들어간다
console.log(greet("홍길동", "이순신")); // 첫번째 인수만 사용됨

// 매개변수 기본값 활용
function hi(name = "아무개") { // 인수가 전달되지 않거나 undefined가 들어오면 "아무개"가 기본값으로 쓰임
    return `${name} 안녕?`;
}

console.log(hi());
console.log(hi("유관순"));
console.log(hi(undefined));