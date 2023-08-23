$(function () {
    var index = 0;

    $('.title-menu').mouseover(function () {
        index = $(this).index();
    });

    $('.title-menu').hover(function () {
        $('.title-menu').eq(index).css({
            "textDecoration": "underLine",
            "color": "rgb(124, 124, 130)",
        });
        $('#header-sub').css({
            'height': 400,
            'transform': 'scaleY(1)',
        });
        $('#header-sub>div').eq(index).css("backgroundColor", "#DBDBDB");
    }, function () {
        $('.title-menu').eq(index).css({
            "textDecoration": "none",
            "color": "black",
        });
        $('#header-sub').css({
            'height': 0,
            'transform': 'scaleY(0)',
        });
        $('#header-sub>div').eq(index).css("backgroundColor", "white");
    });

    $('#header-sub').hover(function () {
        $('#header-sub').css({
            'height': 400,
            'transform': 'scaleY(1)',
        });
    }, function () {
        $('#header-sub').css({
            'height': 0,
            'transform': 'scaleY(0)',
        });
    });

    $('#header-sub>div').hover(function () {
        $(this).css("backgroundColor", "#DBDBDB");
        $('.title-menu').eq($(this).index()).css({
            "textDecoration": "underLine",
            "color": "rgb(124, 124, 130)",
        });
    }, function () {
        $(this).css("backgroundColor", "white");
        $('.title-menu').eq($(this).index()).css({
            "textDecoration": "none",
            "color": "black",
        });
    });
});