$(function () {
    var textBtn = $('.main-text-btn');
    textBtn.eq(2).hide(); // 시작 버튼 숨기기
    var textY = 0;
    var textTlY = ($('.text').length - 1) * -100;       // text 수 * 100 -> translateY
    var repeatNotice = setInterval(noticeDown, 5000);   // 5초마다 반복

    // 공지 버튼 클릭시
    $('.main-text-btn').children().click(function () {
        buttonCheckControl($(this).attr('id'));
    });

    function buttonCheckControl(btn) {
        if (btn == 'notice-up') {
            return noticeUp();
        } else if (btn == 'notice-down') {
            return noticeDown();
        } else if (btn == 'notice-play') {
            textBtn.eq(1).show();
            textBtn.eq(2).hide();
            return repeatNotice = setInterval(noticeDown, 5000);
        }
        textBtn.eq(2).show();
        textBtn.eq(1).hide();
        return clearInterval(repeatNotice);
    }

    function noticeUp() {
        textY < 0 ? textY += 100 : textY = textTlY;
        return setTrnaslateY();
    }

    function noticeDown() {
        textY > textTlY ? textY -= 100 : textY = 0;
        return setTrnaslateY();
    }

    function setTrnaslateY() {
        $('.text').each(function () {
            $(this).css("transform", "translateY(" + textY + "%)");
        });
    }
});
