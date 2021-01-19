let index = { // object이기 때문에 아무일도 일어나지 않음
    init:function (){
        // jQuery 사용
        $("#btn-delete").on("click",() => {
            this.delete();
        });
        $("#btn-update").on("click",() => {
            this.update();
        });
    },

    delete: function (id){
        let pw = prompt("비밀번호를 입력해주세요", '')
        $.ajax({
            type:"POST",
            url:"/check",
            data:pw,
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        }).done(function (result){
            console.log(result);
            if(result === true){
                $.ajax({
                    type:"DELETE",
                    url:"/delete/" + id,
                    dataType: "json"
                }).done(function (result){
                    console.log(result);
                    alert("삭제 완료");
                    location.href = "/admin";
                });
            }
            else {
                alert("비밀번호 오류");
                location.href = "/admin";
            }
        });
    },

    update: function () {
        //alert("user 의 save 함수 호출");
        let id = $("#id").val();
        let data2 = {
            name: $("#name").val(),
            category: $("#category").val(),
            ewhaType: $("#ewhaType").val(),
            phone: $("#phone").val(),
            address: $("#address").val(),
            menuName: $("#menuName").val(),
            price: $("#price").val(),
            url: $("#url").val(),
            imageUrl: $("#imageUrl").val(),
        }

        let pw = prompt("비밀번호를 입력해주세요", '')

        $.ajax({
            type:"POST",
            url:"/check",
            data:pw,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (result) {
            if (result === true) {
                $.ajax({
                    type: "PUT",
                    url: "/update/" + id,
                    data: JSON.stringify(data2),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done(function (result) {
                    console.log(result);
                    alert("수정완료");
                    location.href = "/getFood/" + id;
                });
            } else {
                alert("비밀번호 오류");
                location.href = "/getFood/" + id;
            }
        });
    }
}

index.init(); // 이걸 호출하면 index 바인딩