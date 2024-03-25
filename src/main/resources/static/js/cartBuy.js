$(document).ready(function(){
    $("#buyBtn").click(function(){
        var formData = new FormData(); // FormData 객체 생성

        // 체크된 체크박스를 찾아 FormData에 데이터 추가
        $("input[name=checkbox1]:checked").each(function() {
            var row = $(this).closest("tr");
            var iseqValue = row.find("[name='iseq']").val();
            formData.append("iseq", iseqValue); // iseq 추가
            formData.append("quantity", row.find("[name='quantity']").val()); // 수량 추가

            // iseq 값을 alert 창으로 출력
            alert("iseq 값: " + iseqValue);
        });

        // AJAX 요청을 보냄
        $.ajax({
            url: "/shopping/itemsBuy",
            type: "POST",
            processData: false,
            contentType: false,
            data: formData,
            success: function(response) {
                // 성공적으로 요청이 완료된 경우 실행할 코드
                alert("AJAX 요청을 보냈습니다.");
                window.location.href = "/shopping/itemsBuy";
            },
            error: function(xhr, status, error) {
                alert("AJAX 요청 오류: " + error);
            }
        });
    });
});
