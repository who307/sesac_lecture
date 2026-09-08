// 함수도 값이다
const sayHello = function () {
    console.log("안녕하세요~");
};
console.log(typeof sayHello);
sayHello();

// 객체의 프로퍼티 값으로 넣기
const myObject = {
    sayHi: function () {
        console.log("반갑습니다!");
    },
};
console.log(myObject.sayHi);
myObject.sayHi(); //반갑습니다

// 다른 함수에 전달하기
function manager(task, count) {
    console.log(`매니저가 업무를 지시합니다.`);
    for (let i = 0; i < count; i++) {
        task();
    }
    return function(){
        console.log("모든 업무가 완료되었습니다.");
    }
}
const report = manager(sayHello, 3)
report();