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

 // 모달 토글 버튼을 가져옴
 var modalToggle = document.getElementById('addressModalToggle');

 // 모달 요소를 가져옴
 var modal = document.getElementById('addressModal');

 // 모달을 닫는 요소를 가져옴
 var closeBtn = document.getElementsByClassName('close')[0];

 // 모달 토글 버튼 클릭 시 모달을 보이도록 설정
 modalToggle.onclick = function() {
     modal.style.display = 'block';
 }

 // 모달 닫기 버튼 클릭 시 모달을 숨김
 closeBtn.onclick = function() {
     modal.style.display = 'none';
 }

 // 모달 외부 영역 클릭 시 모달을 숨김
 window.onclick = function(event) {
     if (event.target == modal) {
         modal.style.display = 'none';
     }
 }

 // 배송지 목록 중 선택한 배송지 정보 가져오기
 function getAddressDetails() {
     var selectedSeq;
     var radios = document.getElementsByName('selectAddress');

     for (var i = 0; i < radios.length; i++) {
         if (radios[i].checked) {
             selectedSeq = radios[i].value; // 각 라디오 버튼에 할당된 seq 값을 가져옴
             break;
         }
     }

     // AJAX 요청 생성
     $.ajax({
         type: 'GET',
         url: '/member/selectAddress',
         data: { seq: selectedSeq }, // 선택된 seq 값을 전송
         success: function(response) {
             // 성공적으로 받은 데이터를 처리하여 HTML 업데이트
             // 예: 받은 데이터를 사용하여 주소 정보를 표시
             alert("성공~!");

             $('#sample6_postcode').val(response.address.zipCode);
             $('#sample6_address').val(response.address.city);
             $('#sample6_detailAddress').val(response.address.street);
             $('#addressName').val(response.address.name);
             $('#addressPhone').val(response.address.phone);

         },
         error: function(xhr, status, error) {
             console.error('Error:', error);
         }
     });
     closeModal();
 }

 // 모달 닫기
 function closeModal() {
     var modal = document.getElementById('addressModal');
     modal.style.display = "none";
 }

 // 기본 배송지 버튼을 클릭했을 때 실행되는 함수
 function fetchBasicAddress() {
     // AJAX 요청 생성
     $.ajax({
         type: 'GET',
         url: '/member/getBasicAddress', // 기본 배송지 정보를 가져오는 엔드포인트 URL
         success: function(response) {
             // 성공적으로 받은 데이터를 처리하여 HTML 업데이트
             // 예: 받은 데이터를 사용하여 주소 정보를 표시
             $('#addressName').val(response.address.name);
             $('#addressPhone').val(response.address.phone);
             $('#sample6_postcode').val(response.address.zipCode);
             $('#sample6_address').val(response.address.city);
             $('#sample6_detailAddress').val(response.address.street);
         },
         error: function(xhr, status, error) {
             console.error('Error:', error);
         }
     });
 }

 // 새로운 배송지 클릭 시, 값 초기화
 function clearAddress() {
     document.getElementById('addressName').value = ''; // 수령자 입력란 초기화
     document.getElementById('addressPhone').value = ''; // 휴대폰 입력란 초기화
     document.getElementById('sample6_postcode').value = ''; // 우편번호 입력란 초기화
     document.getElementById('sample6_address').value = ''; // 주소 입력란 초기화
     document.getElementById('sample6_detailAddress').value = ''; // 상세주소 입력란 초기화
     document.getElementById('extraAddress').value = ''; // 참고 입력란 초기화
 }
