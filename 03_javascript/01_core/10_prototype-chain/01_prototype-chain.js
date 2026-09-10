const user = {
    id: "user",
    activate: true,
    login: function () {
        console.log(`${this.id}님이 로그인 되었습니다.`);
    },
};

// const user = {
//     id: "user",
//     activate: true,
//     login: function () {
//         console.log(`${this.id}님이 로그인 되었습니다.`);
//     },
// };

// const student = {
//     passion: true,
// };

// create : 새로운 빈 객체를 만들고,
// 그 객체가 프로퍼티를 찾을 때 user도 살펴 볼 수 있도록 연결한다.
// student의 프로토타입을 user로 설정
const student = Object.create(user);
student.passion = true;

console.log(student.activate);
console.log(student);

// student가 프로토타입으로 연결한 객체 확인
console.log(Object.getPrototypeOf(student));

console.log(`자신의 activate`, Object.hasOwn(student, "activate"));
console.log(`자신의 passion`, Object.hasOwn(student, "passion"));

console.log("activate" in student);

const greedyStudent = Object.create(student);
greedyStudent.greed = true;
greedyStudent.id = "student01";

console.log(greedyStudent.activate);
console.log(greedyStudent.passion);
console.log(greedyStudent.missing);

console.log(greedyStudent.id);
console.log(user.id);

// 호출 주체인 점(.)앞의 객체의 this를 참조
greedyStudent.login();

delete greedyStudent.id;
console.log(greedyStudent.id);
greedyStudent.login();
