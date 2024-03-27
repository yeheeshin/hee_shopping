 // 상품들의 총 결제 금액 계산하는 함수
    function calculateTotal() {
    // 모든 상품 항목을 가져옴
    var items = document.querySelectorAll('.table-row.overflow-visible');

    // 총 결제 금액 초기화
    var totalPrice = 0;

    // 각 상품 항목에 대해 반복
    items.forEach(function(item) {
    // 상품 가격과 수량 가져오기
    var price = parseFloat(item.querySelector('.table-cell:nth-child(4)').textContent.replace(' 원', '').replace(',', ''));
    var quantity = parseInt(item.querySelector('.table-cell:nth-child(3)').textContent);

    // 상품 가격과 수량을 곱하여 총 상품 금액에 추가
    totalPrice += price * quantity;
});

        // 총 결제 금액을 출력할 요소 찾기
        var totalElements = document.querySelectorAll('.total-price');

        // 각 요소에 총 결제 금액 추가
        totalElements.forEach(function(element) {
            element.textContent = totalPrice.toLocaleString() + '원';
        });
}

    // 페이지 로드 시 총 결제 금액 계산 실행
    window.onload = function() {
    calculateTotal();
};

