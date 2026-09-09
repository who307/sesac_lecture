/**
 * 생성자 함수
 */

const student1 = {
    name: "판다",
    age: 5,
    getInfo: function () {
        return `${this.name}는 ${this.age}세 입니다.`;
    },
};
const student2 = {
    name: "코알라",
    age: 3,
    getInfo: function () {
        return `${this.name}는 ${this.age}세 입니다.`;
    },
};
console.log(student1.getInfo());
console.log(student2.getInfo());

function Student(name, age) {
    this.name = name;
    this.age = age;
    this.getInfo = function () {
        return `${this.name}는 ${this.age}세 입니다.`;
    };
}

const student3 = new Student("원숭이",40);
const student4 = new Student("코끼리",25);

console.log(student3);
console.log(student4);
console.log(student3.getInfo());
console.log(student4.getInfo());

console.log("같은 객체인가:", student3 === student4);
student3.age = 32;
console.log(student3.getInfo());
console.log(student4.getInfo());