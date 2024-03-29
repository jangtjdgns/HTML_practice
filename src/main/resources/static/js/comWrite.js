$(function () {
    var callFcCount = 0; // 초반 실행 시 함수 호출 횟수 -> 업데이트 시 customInput 값을 지정하기 위함 4번 실행됨 -> 최대 4

    showCelebrateOrMourn();
    selectedCondolence();
    showUserInput();

    $("#occasion").change(function () {
        showCelebrateOrMourn();
    });

    $("#celebrate").add("#mourn").change(function () {
        showUserInput();
    });

    $("#mourn").change(function () {
        selectedCondolence();
    });

    $("#com-name").change(function(){
         $(this).css("borderColor", "black");
    });
    $("#church_officer").change(function(){
        $(this).css("borderColor", "black");
    });
    $("#celebrate").change(function(){
        $(this).css("borderColor", "black");
    });
    $("#mourn").change(function(){
        $(this).css("borderColor", "black");
    });
    $("#condolence").change(function(){
        $(this).css("borderColor", "black");
    });



    // 함수
    function showCelebrateOrMourn() {
        if ($("#occasion").val() === "1") {              // occasion, 경사 == 1
            $(".mourn-wrap").css(
                {
                    visibility: "collapse",
                    height: 0,
                }
            );
            $(".celebrate-wrap").css(
                {
                    visibility: "visible",
                    height: 80,
                }
            );
            $("#mourn").val("none");
            $("#condolence").val("1");                   // condolence, none == 1
        } else {
            $(".celebrate-wrap").css(
                {
                    visibility: "collapse",
                    height: 0,
                }
            );
            $(".mourn-wrap").css(
                {
                    visibility: "visible",
                    height: 80,
                }
            );
            $("#celebrate").val("none");
        }
        showUserInput();
        selectedCondolence();
    }

    function selectedCondolence() {
        if(callFcCount == 4){                       // 함수 호출 횟수가 4이면 customInput 값 초기화
            $(".user_input>input").val("");
        }

        if ($("#mourn").val() == "부고") {
            $("#condolence").prop("disabled", false);
        } else {
            $("#condolence").prop("disabled", true);
            $("#condolence").val("1");              // condolence, none == 1
        }
        callFcCount < 4 ? callFcCount++ : callFcCount;  // 함수 호출 횟수 증가 최대 4까지
    }

    function showUserInput() {
        if(callFcCount == 4){                          // 함수 호출 횟수가 4이면 customInput 값 초기화
            $(".user_input>input").val("");
        }

        if ($("#celebrate").val() === "기타" || $("#mourn").val() === "기타") {
            $(".user_input").css(
                {
                    visibility: "visible",
                    height: 80,
                }
            );
        } else {
            $(".user_input").css(
                {
                    visibility: "hidden",
                    height: 0,
                }
            );
        }
        callFcCount < 4 ? callFcCount++ : callFcCount;      // 함수 호출 횟수 증가 최대 4까지
    }

    $("form").submit(function (event){
        if(required()){
            return event.preventDefault();
        }

        if($("#condolence").prop("disabled")) {
            $("#condolence").prop("disabled", false);
        }

        if(!confirm("등록하시겠습니까?")){
            alert("취소합니다.");
            event.preventDefault();
            selectedCondolence();
        }
    });

    // 유효성 검사
    function required(){
        var isFieldEmpty = false;

        $("#com-name").add($("#church_officer")).add($("#celebrate")).add($("#mourn")).add($("#condolence")).css("borderColor", "black");

        // 성명란 비었을 때
        if(!$("#com-name").val()){
            $("#com-name").css("borderColor", "red");
            isFieldEmpty = true;
        }

        // 직급란 선택안했을 때
        if($("#church_officer").val() === "1"){
            $("#church_officer").css("borderColor", "red");
            isFieldEmpty = true;
        }

        // 경조사 종류 선택안했을 때
        // 1) 경사 이면서 선택 안했을 때
        if($("#occasion").val() === "1" && $("#celebrate").val() === "none" && $("#mourn").val() === "none"){
            $("#celebrate").css("borderColor", "red");
            isFieldEmpty = true;
        }

        // 2) 조사 이면서 선택 안했을 때
        if($("#occasion").val() === "2" && $("#celebrate").val() === "none" && $("#mourn").val() === "none"){
            $("#mourn").css("borderColor", "red");
            isFieldEmpty = true;
        }

        // 2-1) 조사-부고 이면서 부고 구분 선택 안했을 때
        if($("#occasion").val() === "2" && $("#mourn").val() === "부고" && $("#condolence").val() === "1"){
            $("#condolence").css("borderColor", "red");
            isFieldEmpty = true;
        }

        return isFieldEmpty;
    }

    // 썸머노트 높이 변경
    $(".note-editable").css("height", 300);
});