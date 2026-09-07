/**
 * switch문
 * 하나의 변수에 대해 여러 경우를 처리한다.
 */

const fruit = "바나나";

switch (fruit) {
    case "사과":
        console.log("선택한 과일은 사과이다.");
        break;
    case "바나나":
        console.log("선택한 과일은 바나나이다.");
        break;
    case "포도":
        console.log("선택한 과일은 포도이다.");
        break;
    case "망고":
        console.log("선택한 과일은 망고이다.");
        break;
    default: // 위 조건들에 해당하지 않을 때 실행
        console.log("알 수 없는 과일입니다.");
}
