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
