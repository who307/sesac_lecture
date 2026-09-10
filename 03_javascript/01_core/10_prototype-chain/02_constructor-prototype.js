/*
const student1 = {
    name: '판다',
    age: 8
};

const student2 = {
    name: '다람쥐',
    age: 5,
};
*/

function Student(name, age) {
    this.name = name;
    this.age = age;
}

const student1 = new Student('판다', 8);
const student2 = new Student('다람쥐', 4);

Student.prototype.activate = true;
Student.prototype.getInfo = function() {
    return `${this.name}는 ${this.age}세 입니다.`;
}

// new 키워드를 이용해서 만들면 생성자함수의 prototype(Student.prototype) 객체에 연결된다.
const student3 = new Student('홍길동', 20);
const student4 = new Student('장보고', 30);

console.log(Object.getPrototypeOf(student3) === Student.prototype);
console.log(Object.hasOwn(student3, 'getInfo')); // 공통 객체에 정의해놨기 때문에 false

console.log(student3.activate);

console.log(student3.getInfo());
console.log(student4.getInfo());

console.log(student3.getInfo === student4.getInfo); // true

student3.age = 35;
console.log(student3.getInfo());
