// ==========================================
// 1. ES2023: 불변성(Immutability) 배열 메서드
// ==========================================
const numbers = [3, 1, 4, 2];

// [핵심] 기존 sort()는 원본 배열을 변형(mutation)시키지만,
// toSorted()와 with()는 원본을 보존하고 '새로운 배열'을 반환합니다. (React 상태 관리에 매우 중요)
const sorted = numbers.toSorted(); // [1, 2, 3, 4] -> 정렬된 새 배열 반환
const updated = numbers.with(1, 99); // [3, 99, 4, 2] -> 1번 인덱스 요소만 99로 교체한 새 배열 반환

console.log(numbers); // [3, 1, 4, 2] -> 원본 배열은 훼손되지 않고 그대로 유지됨

// ==========================================
// 2. Set을 활용한 원시값 배열 중복 제거
// ==========================================

const oldUniqueNumbers = [1, 2, 2, 3, 4, 4, 5].filter((item, index) => {
    // indexOf(item): item이 처음 발견된 인덱스
    // index: 현재 순회 중인 인덱스
    return [1, 2, 2, 3, 4, 4, 5].indexOf(item) === index;
});
console.log(oldUniqueNumbers); // [1, 2, 3, 4, 5]

// [핵심] Set은 중복을 허용하지 않는 생성자 함수(객체)입니다.
// 전개 연산자([...])를 사용해 Set 객체의 요소들을 다시 '일반 배열'로 얕은 복사해 가져옵니다.
const uniqueNumbers = [...new Set([1, 1, 2, 3, 4, 4, 5, 6, 6])];
console.log(uniqueNumbers); // [1, 2, 3, 4, 5, 6]

// ==========================================
// 3. 객체 배열 중복 제거 시 주의점 (참조 비교)
// ==========================================
const users = [
    { id: 1, name: "Kim" },
    { id: 1, name: "Kim" },
];

// ❌ [핵심] 객체는 값이 아닌 '메모리 주소(참조)'로 비교되므로 Set만 직접 써선 중복 제거가 안 됩니다.
const wrong = [...new Set(users)]; // 중복 제거 실패 (요소 2개 그대로 유지)

// ⭕ [핵심] 고유 키(id 등)를 Set(seen)에 기록하면서 filter()로 거르는 패턴을 사용해야 합니다.
const seen = new Set();
const uniqueUsers = users.filter((user) => {
    if (seen.has(user.id)) return false; // 이미 등장한 ID면 제외
    seen.add(user.id); // 처음 본 ID면 Set에 기록
    return true; // 배열에 포함
});

// ==========================================
// 4. ES2024: Set 표준 집합 연산 메서드
// ==========================================
// [핵심] 과거 filter/includes 조합으로 직접 구현하던 집합 연산을 전용 메서드로 지원합니다.
const setA = new Set([1, 2, 3]);
const setB = new Set([2, 3, 4]);

const intersection = setA.intersection(setB); // Set(2) { 2, 3 } -> 교집합
const difference = setA.difference(setB); // Set(1) { 1 }    -> 차집합 (A - B)

// ==========================================
// 5. ES2024: Object.groupBy() 데이터 그룹화
// ==========================================
const inventory = [
    { name: "사과", category: "과일" },
    { name: "당근", category: "채소" },
    { name: "바나나", category: "과일" },
];

// [핵심] 과거 Array.prototype.reduce()로 길게 작성하던 그룹화 작업을 단 한 줄로 처리합니다.
// 콜백 함수가 리턴하는 값(category)이 결과 객체의 Key가 됩니다.
const result = Object.groupBy(inventory, (item) => item.category);

/*
[출력 결과]
{
  과일: [{ name: "사과", category: "과일" }, { name: "바나나", category: "과일" }],
  채소: [{ name: "당근", category: "채소" }]
}
*/
