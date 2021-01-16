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
        $.ajax({
            type:"DELETE",
            url:"/delete/" + id,
            dataType: "json"
        }).done(function (result){
            console.log(result);
            alert("삭제 완료");
            location.href = "/admin";
        });
    },

    update: function (){
        //alert("user 의 save 함수 호출");
        let id = $("#id").val();
        let data = {
            name: $("#name").val(),
            category: $("#category").val(),
            ewhaType: $("#ewhaType").val(),
            url: $("#url").val(),
            imageUrl: $("#imageUrl").val(),
        }
        $.ajax({
            type:"PUT",
            url:"/update/" + id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType: "json"
        }).done(function (result){
            console.log(result);
            alert("수정완료");
            location.href = "/admin";
        });
    }
}

index.init(); // 이걸 호출하면 index 바인딩