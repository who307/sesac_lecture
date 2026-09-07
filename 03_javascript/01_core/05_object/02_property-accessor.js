/** 프로퍼티 접근
 * 1. 마침표 표기법
 * 2. 대괄호 표기법
 */

const dog = {
    name: "뽀삐",
    eat: function (food) {
        console.log(`${this.name}는 ${food}를 맛있게 먹어요.`);
    },
};
console.log(dog.name);
dog.eat("두부");

// 대괄호 표기법
console.log(dog["name"]); // 프로퍼티 키를 문자열로 적는다
dog["eat"]("고구마");

const obj = {
    "dash-key": "dash-value",
    0: 1,
};
console.log(obj["dash-key"]);
// console.log(obj.dash-key); // error
console.log(obj[0]);

// key가 변수에 담겨 있을 때 반드시 대괄호 표기법을 써야 한다.

const prop = "name";

console.log(dog.prop); // "prop"이라는 프로퍼티 키를 찾으려고 함(undefined)
console.log(dog[prop]); // dog["name"] -> 뽀삐