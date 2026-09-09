// 1. Book(title, price) 생성자를 작성하고 제목과 가격을 프로퍼티에 저장합니다.
// 2. getTotal(quantity) 메서드는 현재 책 가격과 수량을 곱해 반환합니다. 내부에서는 출력하지 않습니다.
// 3. ‘자바스크립트 입문’ 15000원과 ‘객체와 함수’ 20000원인 책 두 개를 new로 만듭니다.
// 4. 두 책의 두 권 총액을 출력합니다. 첫 책의 총액은 const 변수에 저장한 뒤 출력합니다.
// 5. 첫 책 가격을 18000으로 바꾸고 두 책의 두 권 총액을 다시 출력합니다.
// 6. 첫 책의 수량 0도 확인합니다.

function Book(title, price) {
    this.title = title;
    this.price = price;
    this.getTotal = function (quantity) {
        return quantity * this.price;
    };
}

const book1 = new Book("자바스크립트 입문", 15000);
const book2 = new Book("객체와 함수", 20000);
const bookPrice1 = book1.getTotal(2);

console.log(bookPrice1);
console.log(book2.getTotal(2));

book1.price = 18000;
console.log(book1.getTotal(2));
console.log(book1.getTotal(0));
