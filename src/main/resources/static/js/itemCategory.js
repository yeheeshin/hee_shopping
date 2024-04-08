$(document).ready(function () {
    $("#itemColor").change(function () {
        // select 요소 가져오기
        var selectElement = document.getElementById("itemColor");
        // 선택된 옵션 가져오기
        var selectedOption = selectElement.options[selectElement.selectedIndex];
        // 선택된 옵션의 값(value) 반환
        var selectCategory = selectedOption.value;

        var categoryValue = document.getElementById("category").value;

        var requestData = {
            category : categoryValue,
            selectColor : selectCategory
        }

        $.ajax( {
            url : "/shopping/selectCategory",
            type : "POST",
            data : JSON.stringify(requestData),
            contentType : "application/json",
            success: function (response) {
                window.location.href = "/shopping/getColor";
            },
            error: function (xhr, status, error) {
                alert("error");
            }
        });
    });

});