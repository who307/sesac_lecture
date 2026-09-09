// 1. 이름과 나이를 전달받는 `Dog(name, age)` 생성자 함수를 작성합니다.
// 2. 전달받은 값을 새 객체의 name과 age 프로퍼티에 저장합니다.
// 3. `new`를 사용해 뽀삐 3살과 바둑이 5살 객체를 만듭니다.
// 4. 두 객체의 이름과 나이를 출력합니다.
// 5. 첫 번째 강아지의 나이만 4로 바꾸고 두 객체의 나이를 다시 출력합니다.

function Dog(name, age) {
    this.name = name;
    this.age = age;
}

const dog1 = new Dog("뽀삐", 3);
const dog2 = new Dog("바둑", 5);

console.log(dog1.name, dog1.age);
console.log(dog2.name, dog2.age);

dog1.age = 4;
console.log(dog1.age);
console.log(dog2.age);
