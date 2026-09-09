"use strict"; // 엄격 모드

function Student(name, age) {
    console.log(`this가 있는가:`, this !== undefined);
    console.log(`name이 있는가`, "name" in this);
    this.name = name;
    this.age = age;
    this.getInfo = function () {
        return `${this.name}는 ${this.age}세 입니다.`;
    };
    console.log(`초기화한 값: `, this.name, this.age);
}

const student1 = new Student("호랑이", 10);
console.log(`객체를 반환받았는가:`, typeof student1 === "object");

function Dog(name, age) {
    // new.target: new Dog를 호출하면 Dog를 가리키고 일반 호출로 하면 undefined이다.
    if (!new.target) {
        console.log("new 없이 호출했습니다 new를 붙여서 다시 실행합니다.");
        return new Dog(name, age);
    }
    this.name = name;
    this.age = age;
}

const dog = new Dog("바둑이", 3);
console.log(dog);

for (let key in dog) {
    console.log(key);
}
