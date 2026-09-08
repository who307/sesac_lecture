function add(a, b) {
    console.log(a + b);
    // return a + b; // 결과값을 밖으로 던진다
}

const result = add(10, 20); // 던진 값을 result 변수에 담는다

console.log(result);
console.log(result * 2);

function printAdd(a, b) {
    console.log(a + b);
}
function returnAdd(a, b) {
    return a + b;
}

const printed = printAdd(10, 20);
const returned = returnAdd(10, 20);
console.log("printed", printed);
console.log("returned", returned);

// 함수의 종료
function sayHello(name) {
    return `${name}님 안녕하세요!`;
    // return을 만나는 순간 함수 실행 끝남, return 아래의 코드는 실행되지 않는다.
    console.log("출력이 되나요?");
}
console.log(sayHello("유관순"));

// 반환값의 생략
function noReturn(){
    console.log("함수 호출됨");
    return; // 값을 명시하지 않음
}
function emptyFunction(){
    // return문 자체 없음
}
console.log(noReturn()); //undefined
console.log(emptyFunction()); //undefined

// 조기 종료 early return
function registerUser(nickname){
    // 1. 조건이 안 맞으면 즉시 종료 (Early Return)
    if(nickname.length < 2){
        console.log("닉네임이 너무 짧습니다");
        return; // 여기서 함수를 끝냄
    }
    // 2. 위 조건을 통과해야만 실행되는 메인 로직
    console.log(`${nickname}님 환영합니다~`);
}
registerUser("홍")
registerUser("홍길동")