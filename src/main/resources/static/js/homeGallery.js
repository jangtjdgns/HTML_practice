var slides = document.querySelector('.slides'),
    slide = document.querySelectorAll('.slides li'),
    currentIdx = 0,
    slideCount = slide.length,
    prevBtn = document.querySelector('#prev'),
    slideWidth = 480,   // 슬라이드 너비
    slideMargin = 60,   // 슬라이드 마진
    nextBtn = document.querySelector('#next'),
    radioWrap = document.querySelector('.main-images-input-radio'),
    radioLength = 0;

/* radio */
// input radio를 li 수 만큼 추가
slide.forEach(function () {
    var addRadioBtn = document.createElement("input");
    addRadioBtn.type = 'radio';
    addRadioBtn.name = 'select-iamge';
    addRadioBtn.id = `inputRadio${setNum(radioLength)}`;
    addRadioBtn.className = 'input-radio-btn';
    radioLength++;

    var addLabel = document.createElement("label");
    addLabel.htmlFor = addRadioBtn.id;

    if (radioLength == 2) {
        addRadioBtn.checked = true;
    }

    radioWrap.appendChild(addRadioBtn);
    radioWrap.appendChild(addLabel);
});

// 10 이하일때 앞에 0 붙임
function setNum(num) {
    return num < 10 ? `0${num}` : num;
}


// getRadioBtn
var radioBtn = document.querySelectorAll('.input-radio-btn');

// input radio 클릭 시 해당 이미지로 이동
radioBtn.forEach(function (e) {
    e.addEventListener("click", () => {
        moveSlide(parseInt(e.id.slice(-2) - 1));
    });
});



/* slide */
// ul width
slides.style.width = (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

function moveSlide(num) {
    slides.style.left = -num * 540 + 'px';
    currentIdx = num;
    radioBtn[currentIdx + 1].checked = true;    // 이전, 다음 버튼 클릭시 라디오 버튼 체크
}

nextBtn.addEventListener('click', function () {
    if (currentIdx < slideCount - 2) {
        moveSlide(currentIdx + 1);
    } else {
        moveSlide(-1);
    }
});

prevBtn.addEventListener('click', function () {
    if (currentIdx > -1) {
        moveSlide(currentIdx - 1);
    } else {
        moveSlide(slideCount - 2);
    }
});
