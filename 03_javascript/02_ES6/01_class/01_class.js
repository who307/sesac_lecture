class Student {
    // new Student(..)가 실행될 때 자동으로 호출되는 초기화 메서드
    constructor(name, score) {
        this.name = name;
        this.score = score;
    }
    // 객체마다 함수를 새로 만들지 않고
    // Student.prototype에 한 번 만들어 공유한다
    getInfo() {
        return this.name + ":" + this.score + "점";
    }
}

const student1 = new Student("홍길동", 80);
const student2 = new Student("유관순", 90);

console.log(student1);
console.log(student2);
console.log(student1.getInfo());
console.log(student2.getInfo());
