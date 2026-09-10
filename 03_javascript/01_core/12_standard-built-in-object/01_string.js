/** String 표준 빌트인 객체
 * 문자열은 변경할 수 없는 값이므로 메서드를 실행해도 원본은 바뀌지 않고 새로운 값을 반환한다.
 */

// 1. trim - 문자열 앞뒤 공백 제거
const rawKeyword = "      JavaScript      ";
console.log(rawKeyword);
const trimmedKeyword = rawKeyword.trim();
console.log(trimmedKeyword);

// 2. 영문 대소문자 정리 (toLoweCase(), toUpperCase())
const keyword = trimmedKeyword.toLowerCase();
console.log(keyword);
console.log(keyword.toUpperCase());

// 3. indexOf() : 검색어가 시작하는 인덱스 반환, 찾지 못하면 -1을 반환한다.
const title = "JavaScript 배열 수업";
const searchTitle = title.toLowerCase();
console.log(searchTitle.indexOf(keyword));
console.log(searchTitle.indexOf("python"));

// includes() : 검색어가 들어있는지를 true 또는 false로 반환
console.log(searchTitle.includes(keyword));
console.log(searchTitle.includes("python"));

// 4. slice() - 필요한 부분 가져오기
const fileName = "my-profile.jpg";
// slice(시작, 끝)는 시작 인덱스부터 끝 인덱스 바로 앞까지 잘라 반환
console.log(fileName.slice(fileName.indexOf(".")));

// lastIndexOf(".") : 문자열에서 마지막 마침표(.)의 인덱스를 찾는다.
const dotIndex = fileName.lastIndexOf(".");
console.log(dotIndex);

const extension = fileName.slice(dotIndex);
console.log(extension);

// 5. split - 문자열을 배열로 나누기
const tags = "#자바스크립트#개발자#프론트엔드";
// const tagParts = tags.split("#");
// console.log(tagParts);

// 6. 배열 메서드로 빈 값 제외
// const tagList = tagParts.filter((tag) => tag !== "");
// console.log(tagList);

// 메서드 체이닝 방식
const tagList = tags.split("#").filter((tag) => tag !== "");
console.log(tagList);