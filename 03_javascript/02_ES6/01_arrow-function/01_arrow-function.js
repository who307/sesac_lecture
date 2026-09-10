/**화살표 함수 */
"use strict";

const power = function (x) {
    return x * x;
};

const arrowPower = (x) => {
    return x * x;
};
console.log(power(3));
console.log("화살표 함수", arrowPower(3));

const square = (x) => x * x;
console.log(square(3));

// 매개변수 없거나 여러개인 경우, 소괄호 생략 불가
const greet = () => "안녕하세요";
const add = (a, b) => a + b;
console.log(greet());
console.log(add(1, 2));

const calculateSquare = (x) => {
    return x * x;
};

// 중괄호 본문 : return을 꼭 명시해서 반환해야함
const wrongSquare = (x) => {
    x * x;
};

console.log(calculateSquare(3));
console.log(wrongSquare(3)); // undefined

// 객체를 반환할 시, 소괄호로 감싸서 실행문이 아니고
// 반환할 객체를 만드는 표현식임을 표시해 주어야 한다.
const createUser = (id, name) => ({ id: id, name: name });
console.log(createUser(1, "홍길동"));

const createItem = (id, name) => {
    return {
        id: id,
        name: name,
    };
};
console.log(createUser(1, "장난감"));

// 화살표 함수도 다른 함수에 콜백으로 전달 가능
function calculate(value, operation) {
    return operation(value);
}
console.log(calculate(3, power));
console.log(calculate(3, number => number * number));
