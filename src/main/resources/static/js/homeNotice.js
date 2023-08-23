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

    // 누른 버튼 확인하는 함수
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

    // 업 버튼 클릭 시
    function noticeUp() {
        textY < 0 ? textY += 100 : textY = textTlY;
        return setTrnaslateY();
    }

    // 다운 버튼 클릭 시
    function noticeDown() {
        textY > textTlY ? textY -= 100 : textY = 0;
        return setTrnaslateY();
    }

    // text클래스 트렌스폼 적용
    function setTrnaslateY() {
        $('.text').each(function () {
            $(this).css("transform", "translateY(" + textY + "%)");
        });
    }
});
