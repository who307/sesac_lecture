// 1. `title: '자바스크립트 입문'`, `price: 15000`을 가진 book 객체를 만듭니다.
// 2. `calculateTotal(book, quantity)` 함수를 선언합니다.
// 3. 함수에서 book의 가격과 수량을 곱해 반환합니다. 함수 안에서는 출력하지 않습니다.
// 4. 두 권의 총액을 변수에 저장하고 출력합니다.
// 5. book의 가격을 18000으로 변경하고 두 권의 총액을 다시 호출해 출력합니다.
// 6. 수량 0도 호출해 확인합니다.
// 7. `const regularPrice = function(total) { ... };` 형태의 함수 표현식으로 총액을 그대로 반환하는 함수를 만듭니다.
// 8. 같은 방식으로 총액에서 3000원을 빼서 반환하는 `discountPrice`를 만듭니다. 총액이 3000원 미만이면 조기 반환(Early Return)으로 0을 반환합니다.
// 9. `checkout(book, quantity, pricePolicy)`를 선언합니다. 내부에서 `calculateTotal`로 총액을 구하고, `pricePolicy` 콜백(regularPrice 또는 discountPrice)에 총액을 전달한 결과를 반환합니다.
// 10. 가격이 18000원인 현재 book으로 두 권의 일반 결제와 할인 결제를 호출해 결과를 바깥에서 출력합니다. 수량 0의 할인 결제도 확인합니다. 콜백은 `regularPrice`, `discountPrice`처럼 함수 자체를 전달합니다.

const book = {
    title: "자바스크립트 입문",
    price: 15000,
};

function calculateTotal(book, quantity) {
    return book.price * quantity;
}

const totalPrice1 = calculateTotal(book, 2);
console.log(`책 가격 15000원일 때 총액: ${totalPrice1}`);

book.price = 18000;
const totalPrice2 = calculateTotal(book, 2);
console.log(`책 가격 18000원일 때 총액: ${totalPrice2}`);

const totalPrice3 = calculateTotal(book, 0);
console.log(`수량 0개일 때 총액 : ${totalPrice3}`);

const regularPrice = function (total) {
    return total;
};

const discountPrice = function (total) {
    if (total < 3000) return 0;
    return total - 3000;
};

const checkout = function (book, quantity, pricePolicy) {
    const totalPrice = calculateTotal(book, quantity);
    return pricePolicy(totalPrice);
};

console.log(`일반 결제 총액 : ${checkout(book, 2, regularPrice)}`);
console.log(`할인 결제 총액 : ${checkout(book, 2, discountPrice)}`);
console.log(`수량 0개인 할인 결제 총액 : ${checkout(book, 0, discountPrice)}`);
