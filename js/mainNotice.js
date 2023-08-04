var upDownBtn = Array.from(document.getElementsByClassName("main-text-btn"));
upDownBtn[2].style.display = "none"; // 먼저 시작 버튼 숨기기

var text = document.getElementsByClassName("text");
var textY = 0;  // text translateY %
var textMax = (text.length * 100) - 100;    // text 수


// 공지사항 5초마다 자동으로 넘김
var startBtn = setInterval(noticeDown, 5000);

function noticeUp() {
    if (textY < 0) {
        textY += 100;
    } else {
        textY = -textMax;
    }
    return textTranslateY();
}

function noticeDown() {
    if (textY > -textMax) {
        textY -= 100;
    } else {
        textY = 0;
    }

    return textTranslateY();
}

// up, down 체크
upDownBtn.forEach(function (e) {
    e.addEventListener("click", function () {
        var play = e.children[0].className.slice(19, 29);
        playCheck(play, e);
    })
})

// up, down 체크후 이동
function playCheck(play, e) {
    if (play == 'caret-up') {
        return noticeUp();
    } else if (play == 'caret-down') {
        return noticeDown();
    } else if (play == 'play') {
        upDownBtn[1].style.display = "block";
        upDownBtn[2].style.display = "none";
        return startBtn = setInterval(noticeDown, 5000);
    } else {
        upDownBtn[1].style.display = "none";
        upDownBtn[2].style.display = "block";
        return clearInterval(startBtn);
    }
}

// text transfrom 적용
function textTranslateY() {
    for (var y of text) {
        y.style.transform = "translateY(" + textY + "%)";
    }
}
