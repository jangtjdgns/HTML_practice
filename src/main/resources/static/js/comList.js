$(function(){

    // 모달창 =====================================
    $(".open-modal").click(function(){
        var $comId = $(".com-id");
        console.log($(this).text());

        $("#modal").show();
    });

    $(".close-modal").click(function(){
        $("#modal").hide();
    });

    // ===========================================
});