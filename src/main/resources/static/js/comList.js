$(function(){
    // 모달창 =====================================
    $(".open-modal").click(function(){
        const $id = $(this).next().val();

        // ajax 요청으로 해당 id의 엔티티 가져오기
        $.ajax({
            url: `/church/소통/getComEntity?id=${$id}`,       // controller mapping url
            type: 'GET',
            dateTape: 'json',
            success: function(entityData) {
                console.log("success", entityData);

                processFetchedEntityData(entityData);        // 모달창에 값 넣기
            },
            error: function(xhr, status, error) {
                console.error('API 호출 오류: ', error);
            }
        });

        $("#modal").show();
    });

    $(".close-modal").click(function(){
        $("#modal").hide();
    });

    // 모달 창에 값 넣기
    function processFetchedEntityData(entity){

        // 1. 대상자 성명 + 직분
        $(".com-info1").text(`${entity.name} ${entity.roleDescription}`);

        // 2. 경조사 구분
        $(".com-info2").text(entity.occasionType);

        // 3. 경조사 종류
        if(entity.celebrateName != 'none' && entity.celebrateName != '기타') {
            $(".com-info3").text(entity.celebrateName);
        } else if(entity.mournName != 'none' && entity.mournName != '기타') {
            $(".com-info3").text(entity.mournName);
        } else {
            $(".com-info3").text(entity.customInput);
        }

        // 4. 경조사 종류 상세
        if(entity.occasionType == '조사' && entity.mournName == '부고'){
            $(".com-info4").text(entity.condolenceName);
        } else {
            $(".com-info4").text("-");
        }

        // 5. 경조사 일시
        if(entity.occasionDate === null) {
            $(".com-info5").text("-");
        } else {
            var formatDateTime = entity.occasionDate.replace('T', ' ').substring(0, 16);
            $(".com-info5").text(formatDateTime);
        }

        // 6.경조사 장소
        if(entity.occasionVenue.length == 0) {
            $(".com-info6").text("-");
        } else {
            $(".com-info6").text(entity.occasionVenue);
        }

        // 7. 기타 세부 내용
        if(entity.content.length > 0) {
            $(".com-info7>div").html(entity.content);
        } else {
            $(".com-info7>div").html("<p>-</p>");
        }
    }

    // ===========================================
});