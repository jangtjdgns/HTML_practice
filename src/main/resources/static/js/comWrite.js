$(function () {
    showCelebrateOrMourn();
    selectedCondolence();
    showUserInput();

    if ($(this).val() == "부고") {
            $("#condolence").prop("disabled", false);
        } else {
            $("#condolence").prop("disabled", true);
        }

    $("#occasion").change(function () {
        showCelebrateOrMourn();
    });

    $("#celebrate").add("#mourn").change(function () {
        showUserInput();
    });

    $("#mourn").change(function () {
        selectedCondolence();
    });


    function showCelebrateOrMourn() {
        if ($("#occasion").val() === "경사") {
            $(".mourn-wrap").css("display", "none");
            $(".celebrate-wrap").css("display", "grid");
            $("#mourn").val("none");
            $("#condolence").val("none");
        } else {
            $(".celebrate-wrap").css("display", "none");
            $(".mourn-wrap").css("display", "grid");
            $("#celebrate").val("none");
        }
        showUserInput();
        selectedCondolence();
    }

    function selectedCondolence() {
        $(".user_input>input").val("");
        if ($("#mourn").val() == "부고") {
            $("#condolence").prop("disabled", false);
        } else {
            $("#condolence").prop("disabled", true);
            $("#condolence").val("none");
        }
    }

    function showUserInput() {
        $(".user_input>input").val("");
        if ($("#celebrate").val() === "기타" || $("#mourn").val() === "기타") {
            $(".user_input").css("display", "grid");
        } else {
            $(".user_input").css("display", "none");
        }
    }

    // 썸머노트 높이 변경
    $(".note-editable").css("height", 300);
});