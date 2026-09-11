"use strict";

const prices = [1000, 2000, 3000];

const regularPrices = prices.map(function (price) {
    return price * 2;
});
console.log(regularPrices);

const arrowPrices = prices.map((price) => price * 2);
console.log(arrowPrices);

const theater = {
    store: "청량리역점",
    titles: ["오딧세이", "옵세션"],

    showMovieList: function () {
        console.log(this);
        // // 화살표 함수는 자신만의 this가 없고, 바깥 범위의 this를 사용한다.
        // this.titles.forEach((title) => {
        //     console.log(this.store + ": " + title);
        // });
        this.titles.forEach(function (title) {
            console.log(this.store + ":" + title);
        });
    },
};
theater.showMovieList();
